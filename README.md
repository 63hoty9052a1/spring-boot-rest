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
### ![er図](https://user-images.githubusercontent.com/64893747/81170565-df934c80-8fd5-11ea-9e19-eb17e9d1ab3d.jpg)

- クラス図
### ![クラス図](https://user-images.githubusercontent.com/64893747/81170954-8c6dc980-8fd6-11ea-9c66-774108400552.jpg)

- シーケンス図
```
一覧表示
```
### ![シーケンス図1](https://user-images.githubusercontent.com/64893747/81171137-d9ea3680-8fd6-11ea-99c4-4ee7aa60d596.jpg)
```
一覧表示
```
### ![シーケンス図2](https://user-images.githubusercontent.com/64893747/81171140-db1b6380-8fd6-11ea-92c3-11070485087c.jpg)
```
一覧表示
```
### ![シーケンス図3](https://user-images.githubusercontent.com/64893747/81171142-dbb3fa00-8fd6-11ea-9900-4c1b174b8fc4.jpg)
```
一覧表示
```
### ![シーケンス図4](https://user-images.githubusercontent.com/64893747/81171145-dc4c9080-8fd6-11ea-88c8-cf2b429f1eed.jpg)
