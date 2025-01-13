# PVPGame 插件

## 简介

PVPGame 是一个用于 Minecraft 服务器的多人竞技场插件。它允许玩家创建和加入竞技场，参与激烈的 PVP 战斗。插件提供了丰富的命令和功能，使玩家能够轻松地管理竞技场和参与游戏。

## 主要功能

- **竞技场管理**：创建和管理多个竞技场。
- **玩家参与**：玩家可以加入和离开竞技场。
- **竞技场状态**：显示竞技场的在线状态和游戏状态。
- **死亡计数**：记录和显示玩家的死亡次数。

## 配置

插件的配置文件位于 `plugins/PVPGame/config.yml`。你可以根据需要修改配置文件中的设置，例如竞技场的数量和其他相关参数。
## 命令

### 列出竞技场

- **命令**：`/pvpgame list`
- **功能**：列出所有在线的竞技场及其状态。

### 加入竞技场

- **命令**：`/pvpgame join <arena_id>`
- **功能**：加入指定的竞技场。
- **参数**：
  - `<arena_id>`：竞技场的 ID（从 0 开始）。

### 离开竞技场

- **命令**：`/pvpgame leave`
- **功能**：离开当前所在的竞技场。

## 示例

1. **列出所有竞技场**：
   ```

   /pvpgame list
   ```
2. **加入竞技场 1**：
   ```

   /pvpgame join 1
   ```
3. **离开当前竞技场**：
   ```

   /pvpgame leave
   ```

---
