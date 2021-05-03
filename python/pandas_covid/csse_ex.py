import glob
import os
from sys import path

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
plt.close("all")
import plotly.express as px
import plotly.io as pio
pio.templates.default = "plotly_dark"

path = '../../data/csse/daily/'
all_files = glob.glob(os.path.join(path, "*.csv"))     # advisable to use os.path.join as this makes concatenation OS independent

df_from_each_file = (pd.read_csv(f) for f in all_files)
df   = pd.concat(df_from_each_file, ignore_index=True)

# df = pd.read_csv('../../data/csse/daily/01-21-2021.csv')
df = df[:200]

fatality_by_country = df[['Country_Region', 'Last_Update', 'Deaths']].groupby(['Country_Region', 'Last_Update']).agg({'Deaths': 'sum', 'Country_Region': 'first', 'Last_Update': 'first'})
    # .groupby(['Last_Update']).sum('Deaths')
# subject = fatality_by_country.sort_values(['Case_Fatality_Ratio', 'Last_Update'])
# subject = df[:45].groupby(['Country_Region', 'Province_State'])[['Deaths', 'Confirmed', 'Recovered', 'Active']].max()
# fig = px.line(fatality_by_country, x="Last_Update", y="Deaths",
#              title="Worldwide Confirmed Novel Coronavirus(COVID-19) Fatality Over Time")
# fig.show()

subject = df[df['Country_Region'] == 'China']
# subject = subject.groupby(
#     ['Country_Region', 'Province_State']).min()
# .groupby(['Province_State'])['Deaths', 'Confirmed', 'Recovered', 'Active'].max()

subject = subject[['Province_State', 'Deaths', 'Confirmed', 'Recovered', 'Active']].sort_values('Confirmed', ascending=False)
# pd.set_option('display.max_rows', None)
pd.set_option('display.max_columns', None)

subject = df.groupby(['Country_Region'])[['Deaths', 'Confirmed', 'Recovered']].sum().sort_values('Deaths', ascending=False)

subject = fatality_by_country[(fatality_by_country['Country_Region'] == 'China') | (fatality_by_country['Country_Region'] == 'US')]
    # .pivot(index=['Country_Region'], values='Deaths', columns='Last_Update')
subject['China'] = np.where(subject['Country_Region'] == 'China', subject['Deaths'], 0)
subject['US'] = np.where(subject['Country_Region'] == 'US', subject['Deaths'], 0)
subject = subject.set_index('Last_Update').drop(columns=['Deaths','Country_Region'])

fig = px.line(subject,
             title="US vs. China Deaths Over Time")
fig.show()

plt.figure()
foo = subject.plot()

pass
