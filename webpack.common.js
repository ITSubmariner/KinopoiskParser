const path = require("path");
const webpack = require("webpack");

module.exports = {
    entry: path.join(__dirname, 'src', 'main', 'resources', 'js', 'main.js'),
    mode: "development",
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules|bower_components)/,
                loader: "babel-loader",
                options: { presets: ["@babel/env"] }
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    resolve: {
        modules: [
            path.join(__dirname, "src", "main", "resources", "js"),
            path.join(__dirname, "node_modules")
        ],
        extensions: ["*", ".js", ".jsx"]
    },
    plugins: [new webpack.HotModuleReplacementPlugin()]
};