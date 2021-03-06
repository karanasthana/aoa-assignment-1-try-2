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

    # with open("q1_data.csv", 'r') as file:
    with open("q2_data.csv", 'r') as file:
        csvreader = csv.reader(file)
        for row in csvreader:
            rows.append(row)
    header = ['num_vertices', 'num_edges', 'runtime'];
    return header, rows

def showDataOnGraph(rows):
    for row in rows:
        # if ((int)(row[2]) < 10000 or (int)(row[2]) > (1.5 * 10 ** 10)):
            # continue

        plt.scatter((int)(row[0]), (int)(row[2]), color='blue')

    plt.show()

def main():
    header, rows = readCSVFile()
    showDataOnGraph(rows)

main()