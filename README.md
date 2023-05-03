mvn clean install runs all the tests.
FillInInputFileTest and FillInInputTestWithFewApplesTest just generates little and big input files, according to the x, y and maximum number value is set,
created only for test purpose for different size trees, distinguishes which of algorithms are better for different conditions.

There five algorithms implemented, each of them has it's own test, consist of two tests. Little cell and big cell checked in each test.
HedgehogAstarAlgorythmTest
HedgehogDeikstraBackwardAlgorythmTest
HedgehogEagerAlgorithmTest
HedgehogLooseCorellationTest
RandomHedgehogTest
each test generates log path of hadgehog in src/test/resources, you can look at the path of hedgehog. Different algorytms are working better according to 
input data.
