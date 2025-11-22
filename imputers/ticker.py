import os
import psycopg2
import pandas as pd

directory = r"C:\Users\Skipio\IdeaProjects\das-backend\Податоци"

if __name__ == '__main__':
    try:
        conn = psycopg2.connect(
            host="localhost",
            port="5432",
            database="das",
            user="postgres",
            password="postgres"
        )
        cur = conn.cursor()
        print("Connected!")

        # Loop through all CSV files
        for filename in os.listdir(directory):
            if filename.endswith(".csv"):
                file_path = os.path.join(directory, filename)
                print(f"Processing {filename}...")

                # Load CSV
                df = pd.read_csv(file_path)

                # Get the ticker from the first row
                ticker = str(df.iloc[0]["ticker"])

                # Get the last row (most recent data)
                last_row = df.iloc[-1]

                # Extract values and convert to native Python types
                last_updated = str(last_row["Date"])
                price = float(last_row["Close"])  # Convert numpy.float64 to Python float

                # Check if this ticker already exists
                cur.execute(
                    "SELECT id FROM cryptocurrency WHERE ticker = %s",
                    (ticker,)
                )
                existing = cur.fetchone()

                if existing:
                    # Update existing record
                    cur.execute(
                        """
                        UPDATE cryptocurrency
                        SET price = %s, last_updated = %s
                        WHERE ticker = %s
                        """,
                        (price, last_updated, ticker)
                    )
                    print(f"Updated {ticker}")
                else:
                    # Insert new record
                    cur.execute(
                        """
                        INSERT INTO cryptocurrency (ticker, name, price, last_updated)
                        VALUES (%s, %s, %s, %s)
                        """,
                        (ticker, None, price, last_updated)
                    )
                    print(f"Inserted {ticker}")

                conn.commit()
                print(f"Done: {filename}")

        cur.close()
        conn.close()
        print("Finished processing all CSVs.")

    except Exception as e:
        print("Error:", e)
