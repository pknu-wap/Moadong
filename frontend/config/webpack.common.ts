import * as path from 'path'
import * as webpack from 'webpack'
// plugin
const HtmlWebpackPlugin = require('html-webpack-plugin');

const configuration: webpack.Configuration = {
	// 모듈 해석 방법 설정
    resolve: {
        // 생략할 확장자
        extensions: ['.ts', '.tsx', '.js', '.jsx'],

        // 절대 경로
        alias : {
            "@src" : path.resolve(__dirname, '/src/'),
        },
    },

    entry: "./src/index",

    // 로더
    module: {
        rules: [
            {
                test: /\.(ts|tsx|js|jsx)$/,
                use: ["ts-loader"],
                exclude: /node_modules/,
            },
        ],
    },

    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, '..', 'public', 'index.html'),
        }),
        new webpack.ProvidePlugin({React: "react"}),
    ],
};

export default configuration;