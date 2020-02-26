# Santatecla development and deployment rules

## Git

### **Git flow** branching model:

- **Master:** stores the official release history. All commits will be tagged with a version number. Stable version deployed.

- **Develop:** serves as an integration branch for features. Must be stable.

- **Feature:** these branches use develop as their parent branch. When a feature is complete, it gets merged back into develop (Pull request must be approved). Features should never interact directly with master.

- **Release:** only bug fixes, documentation generation, and other release-oriented tasks should go in this branch. Once it's ready to ship, the release branch gets merged into master and tagged with a version number. In addition, it should be merged back into develop.

- **Hotfix:** used to quickly patch production releases. This is the only branch that should fork directly off of master. As soon as the fix is complete, it should be merged into both master and develop, and master should be tagged with an updated version number.

### Commits:

Format: `<type>(<scope>): <subject>`

`<scope>` is optional

Types:

- `feat`: new feature for the user, not a new feature for build script.
- `fix`: bug fix for the user, not a fix to a build script.
- `docs`: changes to the documentation.
- `style`: formatting, missing semi colons, etc; no production code change.
- `refactor`: refactoring production code, eg. renaming a variable.
- `test`: adding missing tests, refactoring tests; no production code change.
- `chore`: updating grunt tasks etc; no production code change.

[More information: Semmantic commit messages](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716)

### Branches name:

- (type of branch)/name-of-the-branch.
- Cannot use capital letters.
- Each word must be separated by "-".

- Example: `feature/add-index-in-lesson-view`

### Pull requests:

Before merge feature branch into develop, a **Pull request** must be opened, reviewed and approved by **2** contributors.

Pull requests have to be able to be merged into develop automatically: 

- Merge develop into your feature branch and solve conflicts

- Recommended (into feature branch): `git merge --no-ff origin/develop`

After approving and merging pull request, feature branch must be deleted.

