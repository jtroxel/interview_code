import unittest
import pandas as pd
import numpy as np
import random

class PandaSeriesTest(unittest.TestCase):


    def test_series_top2_or_other(self):
        np.random.RandomState(100)
        ser = pd.Series([2,2,2,2,3,3,3,4,4,4,5])
        print(ser[:])

        counts = ser.value_counts()
        print(counts[:])
        vals_in_top_2 = counts.isin(counts[:2])

        tops_vals = counts[vals_in_top_2]

        ser.where(ser.isin(tops_vals), 'Other', True)
        for i, val in enumerate(ser):
            self.assertTrue(val in tops_vals or val == 'Other')
            print(f"✅ {val}")

    def test_series_pos_items_in_other(self):
        ser1 = pd.Series([10, 9, 6, 5, 3, 1, 12, 8, 13])
        ser2 = pd.Series([1, 3, 10, 13])

        test_list = list(ser1[ser1.isin(ser2)].index)
        self.assertEqual(test_list, list([0,4,5,8]))


if __name__ == '__main__':
    unittest.main()
