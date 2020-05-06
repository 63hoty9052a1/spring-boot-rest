## Spring Boot - Rest API

Springフレームワークを使用したRest APIの実装サンプルです。

## 作成目的
技術検証（Rest API）兼、ポートフォリオ用

## アプリケーションの概要
Youtubeの動画（動画のリンクアドレス）を登録することで、その動画に紐付くデータとしてabemaTVやニコニコ動画のようなコメントを追加し、表示することが出来るWebアプリケーションです。

## 機能概要
- Youtubeの動画（動画のリンクアドレス）登録
- 登録した動画の一覧表示、削除、更新
- 登録した動画の再生とコメント追加

## 利用技術（フレームワークおよびライブラリ）
- アプリケーション層
    - フレームワーク：Spring Boot
    - ビルドツール：Maven（Sprign Bootプロジェクト作成時に設定）
    - ORM：Spring Data JPA（内部はHibernateでSprign Bootプロジェクト作成時に設定）
    - HikariCP（Sprign Bootプロジェクト作成時に設定）

- データ層
    - データベース：PostgreSQL

- プレゼンテーション層
    - テンプレートエンジン：Thymelieaf（Sprign Bootプロジェクト作成時に設定）
    - JavaScript
        - Ajax
        - Jquery
        - DataTables

## アプリケーションのUML図
- ER図
    - ![er図](https://user-images.githubusercontent.com/64893747/81170565-df934c80-8fd5-11ea-9e19-eb17e9d1ab3d.jpg)
