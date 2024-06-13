# Changelog

All important changes to this project will be documented in this file.

### Added
- Introduced `CalculatorLogic` class to handle all calculation-related functionalities.
- Created `ThemeLoader` class to handle loading themes from the YAML configuration file.
- Added `ThemeApplier` class to apply themes to UI components, centralizing theme application logic.

### Changed
- Refactored `CalculatorUI` to delegate calculation logic to `CalculatorLogic`.
- Updated `CalculatorUI` to use `ThemeLoader` and `ThemeApplier` for theme management.
- Improved code readability and maintainability by following the single responsibility principle.
- Updated `CalculatorUITest` to ensure proper initialization and testing of `CalculatorLogic` integration.
- Added tests to verify the correct application of themes and calculation functionality.

### Fixed
- Updated GitHub Actions workflow to use Maven instead of Gradle.
- Removed unnecessary files and cleaned up the repository.
- Updated .gitignore to exclude .DS_Store and build directory.
- Removed Gradle files and restored to Maven build system.
- Updated GitHub Actions workflow to use Node.js 20 and updated actions.
- Fixed GitHub Actions workflow configuration.
- Resolved merge conflicts in README.md and LICENSE.

### Added
- Initial setup of the repository with README, LICENSE, CONTRIBUTING guidelines, and test files.
