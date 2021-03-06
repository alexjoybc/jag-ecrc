## eCRC Frontend

In order to connect to the backend api for development, create an env file named .env.development and copy the format of .env.example. Replace <Base_url_of_backend_api> with the url of the back end api (for example: http://localhost:4000), and <Base_url_of_frontend> with the url the frontend will run on (for example http://localhost:3000).

In the project directory, you can run:

### `npm install`

Installs all the required dependencies to get the application up and running.

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br />
You will also see any lint errors in the console.

### `npm run build`

Builds the app for production to the `build` folder.<br />
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br />
Your app is ready to be deployed!

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## ESLint and Prettier

This project uses ESLint and Prettier to ensure that the code being written follows standard guidelines and standards, and that the code styling is kept consistent throughout the application.

### `npx eslint .`

Runs the linter on the entire frontend codebase and reports any errors or warnings that may be present.

### `prettier [opts] [filename ...]`

Runs prettier and formats your file. This has been setup by Husky to run on every commit, so prettier will check all the files within the directory and format them on every commit.

## Storybook

This project uses component-driven development and storybook in order to create stories for frontend components. In order to run the storybook locally, you can run:

### `npm run storybook`

Storybook should start, on a random open port in dev-mode. Now you can develop your components and write stories and see the changes in Storybook immediately since it uses Webpack’s hot module reloading.<br />

Open [http://localhost:9009](http://localhost:9009) to view it in the browser.

## Jest

This project uses Jest for snapshot component testing. Snapshot tests are a very useful tool whenever you want to make sure your UI does not change unexpectedly. A typical snapshot test case renders a UI component, takes a snapshot, then compares it to a reference snapshot file stored alongside the test. The test will fail if the two snapshots do not match: either the change is unexpected, or the reference snapshot needs to be updated to the new version of the UI component.

### `npm run test`

Launches the test runner in the interactive watch mode.

## Environment Variables

In order to ensure the app runs successfully, you will be required to set some environment variables as specified in the `.env.example` file. Please setup a `.env.development` file for local dev and populate the fields shown with the appropriate values.
