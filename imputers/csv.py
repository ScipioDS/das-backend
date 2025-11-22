import os
import pandas as pd

directory = "C:\Users\Skipio\IdeaProjects\das-backend\Податоци"

for filename in os.listdir(directory):
    if filename.endswith(".csv"):
        file_path = os.path.join(directory, filename)

        # Remove .csv
        base = os.path.splitext(filename)[0]

        # Split by "_" and take the first part
        ticker = base.split("_")[0]

        # Load CSV
        df = pd.read_csv(file_path)

        # Add column with ticker
        df["ticker"] = ticker

        # Save CSV
        df.to_csv(file_path, index=False)

        print(f"Updated {filename} → ticker = {ticker}")
