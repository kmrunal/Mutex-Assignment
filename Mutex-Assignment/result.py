import statistics
from collections import defaultdict
import pprint

def processFile(fileName):
    output = open(fileName, "r").read().strip().split('\n')
    map = defaultdict(list)
    for entry in output:
        key = entry.split(':')[0]
        value = entry.split(':')[1]
        map[int(key)].append(int(value))
    
    return map

def calculateMean(map):
    di = {}
    for key in map:
        di[key] = statistics.mean(map[key])
    return di

def calculateStandardDeviation(map):
    di = {}
    for key in map:
        di[key] = statistics.stdev(map[key])
    return di

def calculateTotalMean(map):
    arr = []
    for key in map:
        arr += (map[key])
    return statistics.mean(arr)

def calculateTotalStandardDeviation(map):
    arr = []
    for key in map:
        arr += (map[key])
    return statistics.stdev(arr)

bakeryOutput = processFile("BakeryOutput.txt")
filterOutput = processFile("FilterOutput.txt")
javaLockOutput = processFile("JavaLockOutput.txt")

print ("---------------Mean for N threads respectively-------------------------")
print ()
pprint.pprint (calculateMean(bakeryOutput))
print ()
pprint.pprint(calculateMean(filterOutput))
print ()
pprint.pprint (calculateMean(javaLockOutput))
print ()
print ("--------------StdDev for N threads respectively--------------------")
print ()
pprint.pprint (calculateStandardDeviation(bakeryOutput))
print ()
pprint.pprint(calculateStandardDeviation(filterOutput))
print ()
pprint.pprint (calculateStandardDeviation(javaLockOutput))
print ()
print ("--------------Mean for N threads-------------------------")
print ()
print ("Mean time of execution for Bakery Lock is = " + str(calculateTotalMean(bakeryOutput)))
print ("Mean time of execution for Filter Lock is = " + str(calculateTotalMean(filterOutput)))
print ("Mean time of execution for Java Lock is = " + str(calculateTotalMean(javaLockOutput)))
print ()
print ("-----------------StdDev for N threads respectively---------------------------")
print ()
print ("Standard Deviation of time of execution for Bakery Lock is = " + str(calculateTotalStandardDeviation(bakeryOutput)))
print ("Standard Deviation of time of execution for Filter Lock is = " + str(calculateTotalStandardDeviation(filterOutput)))
print ("Standard Deviation of time of execution for Java Lock is = " + str(calculateTotalStandardDeviation(javaLockOutput)))