# API critique
- team 8

- checkValidParameters
  - internal because used by configuration and visualization

- XMLWriter
  - internal because only configuration would need to call it

- XMLException
  - internal because only configuration would need to call it

- XMLReader
  - internal because only configuration would need to call it

- SimulationInfo
  - all methods external because it must be known to many other classes

In the configuration section, the external methods are the methods that get information about the simulation, e.g. `getTitle`, `getType`, `getSimulation`, etc. These are used by the simulation package.

The internal methods involve XML checking and parsing, which does not need to be known by any other package. But the XMLWriter method should be external as it will be called by the visualization package.
