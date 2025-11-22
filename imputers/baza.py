import os
import psycopg2
import pandas as pd

directory = r"C:\Users\Skipio\IdeaProjects\das-backend\Податоци"  # Added 'r' for raw string

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
                print(f"Inserting {filename}...")

                # Load CSV
                df = pd.read_csv(file_path)

                # Insert row-by-row
                for _, row in df.iterrows():
                    cur.execute(
                        """
                        INSERT INTO crypto_prices (ticker, Date, High, Low, Close)
                        VALUES (%s, %s, %s, %s, %s)
                        """,
                        (row["ticker"], row["Date"], row["High"], row["Low"], row["Close"])
                    )

                conn.commit()
                print(f"Done: {filename}")

        cur.close()
        conn.close()
        print("Finished inserting all CSVs.")

    except Exception as e:
        print("Error:", e)



#CREATE TABLE crypto_prices (
#    id SERIAL PRIMARY KEY,
#    ticker VARCHAR(20),
#    date DATE,
#   high NUMERIC,
#   low NUMERIC,
#   close NUMERIC
#);
