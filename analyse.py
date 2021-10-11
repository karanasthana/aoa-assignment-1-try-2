import csv
import pandas
import datetime as dt
import matplotlib.pyplot as plt
from dateutil.relativedelta import relativedelta
from operator import itemgetter
from numpy import NaN
from IPython.display import display
from statistics import mode

headerIndexDict = {}
spacedDateFormat = '%b %d, %Y'
slashedDateFormat = '%m/%d/%Y'

def readCSVFile():
    rows = []

    # with open("data.csv", 'r') as file:
    with open("q2_data.csv", 'r') as file:
        csvreader = csv.reader(file)
        for row in csvreader:
            rows.append(row)
    header = ['num_vertices', 'num_edges', 'runtime'];
    return header, rows

def showDataOnGraph(rows):
    # tlist = list(zip(*rows))
    # xaxis = tlist[0]
    for row in rows:
        if ((int)(row[2]) < 10000):
            continue
        plt.scatter((int)(row[0]), (int)(row[2]), color='blue')

    plt.show()

def main():
    header, rows = readCSVFile()

    print('number of rows is ', len(rows))

    # Initializing dictionary to store the indices of the new columns
    i=0
    for val in header:
        headerIndexDict[val] = i
        i = i + 1

    showDataOnGraph(rows)

main()