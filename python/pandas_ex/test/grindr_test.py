"""
Your challenge is to implement `write_user_age_summary(...)` below.
"""

import os
import sqlite3  # In-memory SQL database
import unittest

import pandas as pd

common_columns = ['userid', 'age', 'score']

example_csv = 'example.csv'
example_db = 'example.db'


class GrindrUserData:
    def write_user_age_summary(self,
                               csv_file: str, db_file: str, result_file: str,
                               ) -> None:
        """
        TODO: Implement this function.

        This function should read data from a CSV file and a SQLite DB and
        write its results_raw to file in JSON format.

        The function `setup` above creates a CSV and the sqlite3 DB table
        containing the same kind of data: A list of users with a `user_id`,
        `age`, and `score` for each user.

        The function needs to:
        - Read in both datasets
        - Combine them into a single dataset with 3 columns: `userid`, `age`,
          and `score`.
        - Deduplicate by `userid`, keeping only the row with the highest `score`
        - Bucket the rows by decade (all users in their 20's together, 30's
          together, etc.)
        - Compute the mean `score` for users in each decade
        - Write the result out to a JSON file

        Note: there are some issue with the data above that will make it tricky
        to process correctly. Use your common sense to address the issues.
        Document your choices clearly. Also, please explain the exact JSON
        format you're using for the output, and justify your choices.

        Hint: if you do things right, you should find that the mean score
        for users in their 20s for example is 26.1.
        """

        # read and merge
        users = self.get_merged_users(csv_file, db_file)
        # dedupe
        users_distinct = self.dedupe_users(users)

        # bucket by decade
        users_distinct['age_group'] = (users_distinct.age - 5).round(decimals=-1)
        users_bucketed = users_distinct.sort_values('age')

        # mean score by decade
        results_raw = users_bucketed.groupby('age_group').score.transform('mean')
        users_bucketed['age_group_mean'] = results_raw
        # write
        users_bucketed.to_json('users_extra.json')
        return results_raw.reset_index().to_json(result_file)

    def dedupe_users(self, users):
        grouped = users.groupby('userid').agg({'score': 'max', 'age': 'last'}).reset_index()
        return grouped

    def get_merged_users(self, csv, db):
        """
        - Read in both datasets
        - Combine them into a single dataset with 3 columns: `userid`, `age`,
          and `score`.
        """
        user_csv = pd.read_csv(csv, names=common_columns, header=0)
        # tidy up format, all should be ints.  this is what got me b/c it blew up my groupby stuff and I didn't see why
        user_csv.astype(int)
        user_sql = pd.read_sql_query("SELECT * FROM users", sqlite3.connect(db))
        user_sql.rename(columns={'id': 'userid'}, inplace=True)
        # both have same columns and index, so can just concat with index 0
        return pd.concat([user_csv, user_sql], axis=0, join="outer")


class GrindrTest(unittest.TestCase):

    def test_results(self):
        subject = GrindrUserData().write_user_age_summary(example_csv, example_db, "result.json")
        self.assertIsNone(subject)
        self.assertTrue(os.path.exists("result.json"))

    def test_merge_user_dfs(self):
        subject = GrindrUserData().get_merged_users(example_csv, example_db)
        self.assertIsNotNone(subject)
        self.assertEqual(subject.columns.array, common_columns)


def _setup() -> None:
    """
    Creates example CSV and sqlite3 files for use in
    `write_user_age_summary`.
    """

    # Create example.csv
    # '2,41,20\n',
    if os.path.exists(example_csv):
        os.unlink(example_csv)
    with open(example_csv, 'w') as f:
        f.writelines([
            'userid,Age,score\n',
            '1,23,6\n',
            '6,54,300\n',
            '2,39,40\n'])

    # Set up sqlite3 DB
    if os.path.exists(example_db):
        os.unlink(example_db)
    db = sqlite3.connect(example_db)
    cur = db.cursor()
    cur.execute('''
        CREATE TABLE users (
            id integer PRIMARY KEY,
            age integer,
            score float
        )''')
    cur.execute('''
        INSERT INTO users VALUES
            (20, 20, 40.2),
            (21, 33, 100),
            (1, 29, 12)
        ''')
    db.commit()
    db.close()


_setup()
unittest.main(exit=False)
