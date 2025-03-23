# ZP Quantum Computer Simulator

A rather terrible quantum computer simulator made in java.

Note: all thetas are in radians.

To use:

create a Circuit object

use the following single qubit methods:

  .h(int position)

  .x(int position)

  .y(int position)

  .z(int position)

  .rx(int position, double theta)

  .ry(int position, double theta)

  .rz(int position, double theta)

or the controlled gate method:

  .cg(int[] controls positions, int target positions, String gate, double theta)

with gate being "RX", "RY", or "RZ".

or

  .cg(int[] controls positions, int target positions, String gate)

with gate being "X", "Y", "Z".


use

.measure() to measure all qubits once and print the result |note: unlike an actual QC, this does not currently collapse the system|

.printStateVector() to get the state vector as a string

.printProbabilityVector() to get the probabilities as a string

.draw() to get an image of the gates you have added

.printMatrix() to print the matrix that represents the combination of gates you have applied
