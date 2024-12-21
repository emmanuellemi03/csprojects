# -*- coding: utf-8 -*-
"""NetApp.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1Z45ZBOKnooflAu8VmMFSPvwnz8As2HkM
"""

pip install pmdarima

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.arima.model import ARIMA

plt.rcParams['figure.figsize'] = (20.0,10.0)
plt.rcParams.update({'font.size': 12})
plt.style.use('ggplot')

from google.colab import drive
drive.mount('/content/drive')

fruitVegFile = "/content/drive/MyDrive/NetApp Drive/fruitveg.csv"
fruitVeg = pd.read_csv(fruitVegFile)
print('Fruit File shape:', fruitVeg.shape)

fruitVeg_df = pd.DataFrame(fruitVeg)
fruitVeg_df.head(10)

#Optional Standout

#fruitVeg_df_slice = fruitVeg_df.iloc[352:400]

# Plotting
#plt.figure(figsize=(10,6)) # Adjust figure size as needed
#plt.plot(fruitVeg_df_slice['Year'], fruitVeg_df_slice['Value'], marker='o')
#plt.title('Fruit and Vegetable Consumption Over Years')
#plt.xlabel('Year')
#plt.ylabel('Value')
#plt.grid(True)
#plt.show()

##plt.plot(fruitVeg_df['Year'],fruitVeg_df['Value'])

fruitVeg_df_slice = fruitVeg_df.iloc[802:851]

# Plotting
plt.figure(figsize=(10,6)) # Adjust figure size as needed
plt.plot(fruitVeg_df_slice['Year'], fruitVeg_df_slice['Value'], marker='o')
plt.title('Fruit and Vegetable Availability From 1970 to 2019')
plt.xlabel('Year')
plt.ylabel('Value')
plt.grid(True)
plt.show()

def test_stationarity(timeseries):
    # Take the first difference
    timeseries_diff = timeseries.diff().dropna()  # Calculate the first difference and remove NaN values

    # Dickey-Fuller test
    print('Results of Dickey-Fuller Test:')
    dftest = adfuller(timeseries_diff, autolag='AIC')
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic', 'p-value', '#Lags Used', 'Number of Observations Used'])
    for key, value in dftest[4].items():
        dfoutput['Critical Value (%s)' % key] = value
    print(dfoutput)

# Slice the dataframe
fruitVeg_df_slice = fruitVeg.iloc[802:851]  # Rows 802 to 851

# Check stationarity of the sliced data
test_stationarity(fruitVeg_df_slice['Value'])

from pmdarima import auto_arima
# Ignore harmless warnings
import warnings
warnings.filterwarnings("ignore")

stepwise_fit = auto_arima(fruitVeg_df_slice['Value'], trace=True,
süppress_warnings=True)
stepwise_fit.summary ()

from statsmodels.tsa.arima.model import ARIMA

print (fruitVeg_df_slice.shape)
train=fruitVeg_df_slice.iloc[:40]
test=fruitVeg_df_slice.iloc[10:]
print(train.shape, test.shape)

model=ARIMA(train['Value'], order=(1,0,5))
model=model.fit()
model.summary()

start=len(train)
end=len(train)+len(test)-1
pred=model.predict(start=start, end=end, typ='levels')
print(pred)

pred.plot(legend=True)
test['Value'].plot(legend=True)
train['Value'].plot(legend=True)

test["Value"].mean()

from sklearn.metrics import mean_squared_error
from math import sqrt
rmse=sqrt(mean_squared_error(pred,test['Value']))
print(rmse/(721.2845761 -  569.8128596))

mode12=ARIMA(fruitVeg_df_slice['Value'],order=(1,0,5))
mode12=mode12.fit()
fruitVeg_df_slice.tail()

pred=mode12.predict(start=len(fruitVeg_df_slice),end=len(fruitVeg_df_slice)+30,typ='levels').rename('ARIMA Predictions')
print(pred[854])
print(pred[856])
print(pred[861])

plt.figure(figsize=(5, 5))
pred.plot(legend=True)
specific_points_indices = [854, 856, 861]
specific_points_values = pred.loc[specific_points_indices]
plt.scatter(specific_points_indices, specific_points_values, color='red', label='Specific Points')
plt.legend()
plt.show()