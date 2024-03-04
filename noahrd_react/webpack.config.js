const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

const javascriptRules = {
  test: /\.js$/,
  loader: "babel-loader",
  options: {
    presets: [
      [
        "@babel/preset-react",
        {
          runtime: "automatic",
        },
      ],
    ],
  },
};

const cssRules = {
  test: /\.css$/,
  use: ["style-loader", "css-loader"],
};

const rules = [javascriptRules, cssRules];

module.exports = (env, arg) => {
  const { mode } = arg;
  const isProduction = mode === "production";
  return {
    entry: "./src/index.js",
    output: {
      filename: isProduction ? "[name].[contenthash].js" : "main.js",
      path: path.resolve(__dirname, "build"),
    },
    plugins: [new HtmlWebpackPlugin({ template: "src/index.html" })],
    module: { rules },
    devServer: {
      open: true, // open the browser
      port: 3000,
      compress: true,
    },
  };
};
