import unittest
import pandas as pd
import numpy as np


class FramesTest(unittest.TestCase):

    def setUp(self) -> None:
        self.reviews = pd.read_csv("../../../data/wine-reviews/winemag-data-v2_first250.csv", index_col='id')
        # self.reviews.drop(columns=self.reviews.columns[0], inplace=True)
        pd.set_option('display.max_columns', None)

    def test_select(self):
        subject = self.reviews
        # select by slice by position
        self.assertEqual(len(subject[:2]), 2)
        # ... result is a dataframe
        self.assertEqual(type(subject[:2]), pd.DataFrame)

        # select by conditional
        self.assertEqual(len(subject[subject.country == 'Portugal']), 6)

        # select by lambda, project out points, take the max val
        self.assertEqual(subject[lambda df: df.points > 85].points.max(), 92)

    def test_project(self):
        self.assertEqual(len(self.reviews.columns), 13)
        subject = self.reviews[:25].country
        # Select the first from a column projection
        self.assertEqual(subject[1], self.reviews.country[1])
        self.assertEqual(type(subject), pd.Series)

        # Project multiple columns, note list of list
        self.assertIsNotNone(self.reviews[:25][['country', 'title']])


    def test_group_count_sort(self):
        # https://www.kaggle.com/jtroxel/exercise-grouping-and-sorting/edit

        # group by
        subject = self.reviews.groupby('taster_twitter_handle')
        self.assertEqual(subject.taster_twitter_handle.count().sort_values().index[-1], '@vossroger', "Top reviewer = ")

        # ...having (sort of)
        big_reviewers = subject.taster_twitter_handle.value_counts()[lambda c: c > 5]
        self.assertEqual(big_reviewers[big_reviewers > 5].count(), 8, "Number of big reviewers")


    def test_sort_multi(self):

        # getting high_score_val high_scorers of a column
        # multi sort
        subject = self.reviews.sort_values(['points', 'price'])
        # use position index to Select highest points
        high_score_val = subject.points.iloc[-1]
        # Select where points = highest
        high_scorers = subject[subject.points >= high_score_val]
        # Select first/lowest price
        low_price_val = high_scorers.price.iloc[0]
        low_pricers = high_scorers[subject.price <= low_price_val]
        self.assertEqual(low_pricers.iloc[0].points, 92, "Best_rating_per_price = ")
        self.assertEqual(low_pricers.iloc[0].price, 36.0, "Best_rating_per_price = ")

    def test_pivot(self):
        data_url = 'http://bit.ly/2cLzoxH'
        subject = pd.read_csv(data_url)

        # This data is untidy, columns are values not variables (at least year)
        pivoted = subject.pivot_table(values=['lifeExp'], index=['year'], columns='continent', dropna=True)
        pass
if __name__ == '__main__':
    unittest.main()
