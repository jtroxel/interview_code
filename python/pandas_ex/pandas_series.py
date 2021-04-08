import pandas as pd
import numpy as np
import random

np.random.RandomState(100)
ser = pd.Series(np.random.randint(1, 5, [11]))
orig = ser.copy()

counts = ser.value_counts()
print(counts)
top_two = counts.index[:2].union([val for val in counts[2:] if val == counts.index[1]])
print(top_two)
ser.where(ser.isin(top_two), 'Other', True)
print(ser)
print(ser.loc[ser.isin(top_two.to_list())])

for i, val in enumerate(ser):
    assert val in top_two or val == 'Other'
    print(f"✅ {val}")
pass
