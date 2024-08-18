# KMP-Server

Welcome to the KMP (Know My Pill) server repository. This repository serves as the backend foundation for our application, providing critical server-side functionalities and API endpoints. It encompasses the core server logic, including routing, data handling, authentication, and integration with various services. Designed with scalability and maintainability in mind, this server codebase adheres to best practices and coding standards to ensure robust and efficient performance. This repository is integral to the overall architecture, enabling seamless communication between the frontend and backend components of the application.

## 1. Code Convention
For code style and formatting, we adhere to Naver's Code Convention. This set of guidelines ensures consistency and quality across our codebase. The conventions cover a range of topics, including:
- Naming Conventions: Guidelines for naming variables, functions, classes, and other elements.
- Code Structure: Recommendations for organizing code and maintaining readability.
- Formatting Rules: Standards for indentation, line length, and spacing.
- Commenting Guidelines: Best practices for writing meaningful and helpful comments.

For detailed information on Naver’s conventions, please refer to their official documentation [here](https://naver.github.io/hackday-conventions-java/).

## 2. Commit Convention

#### 2-1. Commit Message Structure

Commits take the form of the following:
```
[Commit category]([Scope]): [Brief summary of the changes made in imperative mood]
[If applicable, explain the reason for the changes made]
```

Commit categories are detailed in section 2-2, with scope defined as the specific area of the codebase affected by a single commit. It is recommended that the scope be kept small and manageable to ensure each commit remains atomic.

For example, the following could be a commit of ours.
```
feat(auth): add password reset functionality
```

#### 2-2. Commit Categories

Commits are categorized into one of the following:

- **feat**: The commit involves adding features, domains, methods, or any chunk of code that enhances the project.
- **fix**: The commit involves correcting compile and runtime errors, as well as fixing bugs.
- **style**: The commit involves changes to the code formatting, styling, or whitespace that do not affect the code’s functionality.
- **refactor**: The commit involves restructuring existing code, such as improving code organization or optimizing performance, without changing its external behavior.
- **comment**: The commit involves adding or updating comments to improve code readability and documentation.
- **test**: The commit involves adding or updating tests to ensure code reliability, or fixing existing tests.
- **rename**: The commit involves renaming files, variables, or other elements to improve clarity or conform to naming conventions.
- **remove**: The commit involves removing unused or obsolete code, unused imports, files, or features.
- **chore**: The commit involves routine tasks or maintenance work, such as updating build scripts, managing dependencies, or configuring project settings.
- **!HOTFIX**: The commit resolves a severe issue causing the application to crash in production.

## 3. Branch Strategy

Branches are categorized into one of three domains: feature, test, and bug-fix. Hence, each branch name takes the form of `{Branch Category}/{ID within Category}-{Branch Name}`. 

For example, `feature/01-medication-search` could be a branch name of ours.

Kindly refer to the following to look into our codebase through branches.
```
git branch --list "test/*"
git branch --list "*/foo"
```

git branch --list "feature/*"

## Pull Request Template
