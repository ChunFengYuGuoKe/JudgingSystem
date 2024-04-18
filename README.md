## 基于 AST 的程序源码分析
四川大学软件学院 2023-2024 学年春，软件项目管理课程报告，详情请阅读项目计划书

### 项目介绍

搭建一个面向教学服务的 web-server 服务器，实现基本的用户增删查改功能，对学生作答的代码进行黑盒测试，以及基于 AST 的白盒测试。

### 主要任务

搭建 web-server 服务器，实现 Java 语言判断分析模块，集成现有的 python 和 shell 语言判断分析模块。

### 团队介绍

| 成员  |             项目角色             |
|-----|:----------------------------:|
| 蒋鹏涛 |      项目组组长，Java插件开发工程师       |
| 周仁博 | python与shell插件集成工程师，协助Java插件 |
| 杨开翔 |           后端开发工程师            |
| 吴汉朋 |           测试开发工程师            |
| 李佳员 |           前端开发工程师            |

### 运行项目

#### 启动后端【缺】

#### 启动前端

1. **进入Vue项目目录：** 在命令行中，使用 `cd` 命令进入Vue项目的根目录`frontend`

2. **安装依赖：** 如果您是第一次运行项目，或者项目中的依赖发生了变化，可以运行以下命令安装依赖：

   ```bash
   npm install
   ```

   安装`serve`依赖

   ```bash
   npm install -g serve
   ```

3. **配置信息**

   在 `src/store/modules/ipModule.js`中修改 `localhost:8081` 为实际监听的端口

   ```js
   const state = {
       backendIP: 'http://localhost:8081',
   };
   ```

4. **编译项目：** 一般来说，您可以运行以下命令来编译Vue项目：

   ```bash
   npm run build
   ```

   这将在项目根目录下生成一个名为 `dist` 的目录，其中包含了编译后的静态文件。（接后续步骤）

   也可以运行以下命令启动动态服务器

   ```bash
   npm run serve
   ```

5. **运行项目：** 一旦项目编译完成，您可以通过一个简单的HTTP服务器来运行项目。例如，您可以使用 `serve` 包来在本地运行HTTP服务器：

   ```bash
   serve -s dist
   ```

   这将在本地启动一个HTTP服务器，并将编译后的静态文件提供给客户端访问。您可以在浏览器中输入 `http://localhost:3000`（或者您指定的端口）来访问运行中的Vue项目。

