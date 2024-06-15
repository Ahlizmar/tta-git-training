# Changelog

All important changes to this project will be documented in this file.

### Added
- Introduced `CalculatorLogic` class to handle all calculation-related functionalities.
- Created `ThemeLoader` class to handle loading themes from the YAML configuration file.
- Added `ThemeApplier` class to apply themes to UI components, centralizing theme application logic.
- Added Violet Theme.
- Added new buttons for sine (sin), cosine (cos), and tangent (tan) functions in the Calculator UI.
- Implemented the logic for sine, cosine, and tangent functions in the `CalculatorLogic` class.
- Added unit tests for `CalculatorLogic` mathematical functions.
- Added unit tests for new trigonometric operations.
- Added exception handling for necessary functions.
- Added degrees to radians function.
- Added radians to degrees function.
- Added factorial function.
- Added Log Base 10.
- Added comments to the code for better readability and maintenance.
- Added `testfile.txt` for testing Git commands and features.

### Changed
- Refactored `CalculatorUI` to delegate calculation logic to `CalculatorLogic`.
- Updated `CalculatorUI` to use `ThemeLoader` and `ThemeApplier` for theme management.
- Improved code readability and maintainability by following the single responsibility principle.
- Updated `CalculatorUITest` to ensure proper initialization and testing of `CalculatorLogic` integration.
- Improved logic in `CalculatorUI` to use logic in `CalculatorLogic`.
- Renamed `btnLog` to `btnLN` and moved `ln` logic to `CalculatorLogic`.
- Updated `CalculatorUI` and related classes with new themes and font.

### Fixed
- Expanded unit testing and fixed factorial bugs.
- Removed 'target' folder and updated `.gitignore`.

### Initial Setup
- Initial setup of the repository with `README`, `LICENSE`, `CONTRIBUTING` guidelines, and test files.
