iDME xDM-F v2.25.050、v2.25.060 版本新特性

设计态:
1. 属性约束可根据实体或分类等不同维度进行灵活定义，增强系统灵活性和适用性
2. 支持将整形/长整形属性变更为文本类型，通过类型转换实现属性定义的灵活扩展
3. JDK17发布部署的应用，ID字段支持文本类型配置，兼容雪花ID，UUID等长格式编码的存储需求
4. 业务编码生成器前端交互升级: 通过可视化画布拖拽常量丶属性丶日期丶流水码丶雪花id等编码段编排业务编码规则

运行态
1. 服务编排新增调测日志查看功能，便于快速定位和解决问题
2. 允许用户通过数据服务API获取应用设计态上传的模型图标
3. 支持生成已上传文件的预签名临时链接，直接访问文件
4. 支持对已发放的License文件进行失效处理
5. 新增视图模型管理功能，通过可视化配置实现跨实体查询
6. 支持租户级编码规则隔离，各租户编码规则独立生效
7. 内置模型业务外键属性修改为参考对象属性

配置变更:

| 运行态配置修改： |  |  | 
|  -- | -- | -- |
| 源配置 | 配置项说明 | 修改后的配置 | 
| abm.sync.enable | abm同步开关 | xdm.runtime.abm.sync.enable | 
| abm.tenant.id | abm租户id | xdm.runtime.abm.tenant-id | 
| apicenter.url | API 中心地址 | xdm.apicenter.url | 
| apig.init | APIG 初始化 | xdm.apig.init | 
| application.appId | 应用app ID | xdm.application.app-id | 
| application.id | 应用id | xdm.runtime.application.id | 
| application.subAppId | 应用上下文 | xdm.application.sub-app-id | 
| application.Token | 应用token | xdm.application.token | 
| check.permission.enable | 是否开启下载文件权限校验 | xdm.runtime.check.permission.enable | 
| config.abm.endpoint | abm地址 | xdm.runtime.abm.endpoint | 
| config.abm.instanceId | abm 实例id | xdm.runtime.abm.instance-id | 
| config.abm.projectId | abm peojectId | xdm.runtime.abm.project-id | 
| config.iam.endpoint | iam服务端口地址 | xdm.iam.endpoint | 
| config.iam.login | iam 登陆地址 | xdm.iam.login | 
| config.iam.token.oidc.idpId | 身份提供商ID。 | xdm.iam.token.oidc.idp-id | 
| config.mpc.endpoint | mpc 地址 | xdm.runtime.mpc.endpoint | 
| config.obs.endpoint | obs 地址 | xdm.obs.endpoint | 
| Config.UploadFileType | 上传文件类型 | xdm.runtime.upload-file.type | 
| cross.domain.interception.origin.whitelist.path | orgin 白名单 | xdm.cross.domain.interception.origin.whitelist.path | 
| cross.domain.interception.whitelist.path | referer 白名单 | xdm.cross.domain.interception.whitelist.path | 
| customService.gray | 服务编排是否支持灰度 | xdm.runtime.custom-service.gray.enabled | 
| data.kanban.scheduled.enable | 数据看板定时任务是否可用 | xdm.runtime.data-kanban.scheduled.enable | 
| design.url | 设计态地址 | xdm.runtime.design-url | 
| dme.adapter.client.service.app.instance.id | app 实例id | xdm.adapter.client-service.app-instance-id | 
| dme.config.cloud.native | 是否是本地云 | xdm.cloud.native | 
| dme.data.sync.switch | dme 数据同步开关 | xdm.runtime.data.sync.switch | 
| dme.ignore.ssl.verification | 是否忽略ssl 认证 | xdm.ignore.ssl.verification | 
| dme.search.esUrl | es url地址 | xdm.search.es-url | 
| dme.token.cache.duration | token缓存更新时间 | xdm.token.cache.duration | 
| dme.upload.dataInstance.fileSize | 导入实例接口默认限制50M,单位为Byte | xdm.runtime.upload.data-instance.file-size | 
| dme.upload.fileExtension.fileSize | 导入文件类型白名单接口默认限制2M,单位为Byte | xdm.runtime.upload.file-extension.file-size | 
| dme.upload.unitType.fileSize | 导入计量单位接口默认限制2M,单位为Byte | xdm.runtime.upload.unit-type.file-size | 
| dme.wsf.check.rules | wsf 校验规则 | xdm.runtime.wsf.check.rules | 
| DOCKER_TMP_PATH | docker 目录 | xdm.runtime.docker.tmp-path | 
| es.init | es s是否初始化 | xdm.es.init | 
| file.fastDfs.config.path |  FastDfs客户端配置路径 | xdm.runtime.file.fast-dfs.config.path | 
| full.search.index | es索引名称 | xdm.runtime.full.search.index | 
| fuxi.obs.bucketName | 伏羲obs 桶名称 | xdm.fuxi.obs.bucket-name | 
| git.password |  | xdm.runtime.git.password | 
| git.template.repository |  | xdm.runtime.git.template.repository | 
| git.user |  | xdm.runtime.git.user | 
| hic.env | hic环境 | xdm.hic.env | 
| highCodeOrch.autoSaveInterval | 高代码编排自动保存间隔 | xdm.runtime.high-code-orch.auto-save-interval | 
| hw.cloud.mpc.projectId | mpc 项目id | xdm.runtime.mpc.project-id | 
| hw.cloud.projectId | 项目id | xdm.runtime.hw.cloud.project-id | 
| iam.domain.check | 是否账号 检查 | xdm.iam.domain.check | 
| iam.domain.id.default | 默认租户id | xdm.iam.domain-id.default | 
| iam.domain.tenantDomainId | 租户id | xdm.iam.domain.tenant-domain-id | 
| iam.domain.whiteList | 租户白名单 | xdm.iam.domain.white-list | 
| iit.interceptors.auth.enable | 认证是否可用 | xdm.iit.interceptors.auth.enable | 
| iit.interceptors.token.exclude.uris | 排查url | xdm.iit.interceptors.token.exclude-uris | 
| iit.semaphore.rateLimit | 信号拦截器限制数 | xdm.iit.semaphore.rate-limit | 
| iit.semaphore.timeout | 信号拦截器超时 | xdm.iit.semaphore.timeout | 
| is.interception.http | 是否拦截http请求 | xdm.is.interception.http | 
| isTrialTenant | 是否是体验租户 | xdm.runtime.is-trial-tenant | 
| jwt.workflow.privatekey | jwt电子流私有key | xdm.workflow.jwt.privatekey | 
| jwt.workflow.publickey | jwt电子流公共key | xdm.workflow.jwt.publickey | 
| kafka.url | kafka地址 | xdm.kafka.url | 
| linkx.bootstrapServers | linkx通信kafka配置 | xdm.runtime.linkx.kafka.bootstrap-servers | 
| linkx.kafka.enable.sasl.plain | linkx kafka 采用SASL PLAIN机制 | xdm.runtime.linkx.kafka.sasl.plain.enable | 
| linkx.kafka.password | linkx kafka 密码 | xdm.runtime.linkx.kafka.password | 
| linkx.kafka.username | linkx kafka 用户名 | xdm.runtime.linkx.kafka.username | 
| maven.version.format | 推maven的版本号，格式为1.0.0-env-SNAPSHOT | xdm.maven.version.format | 
| mongodb.connectionsPerHost |  | xdm.mongodb.connections-per-host | 
| mongodb.database | mongodb 数据库 | xdm.mongodb.database | 
| mongodb.init | mongodb 初始化 | xdm.mongodb.init | 
| mongodb.maxWaitTime | mongodb 最大等待时间 | xdm.mongodb.max-wait-time | 
| mongodb.threadConnections | mongoDB 线程连接数 | xdm.mongodb.thread-connections | 
| mongodb.uri | mongoDB 地址 | xdm.mongodb.uri | 
| mongodb.use.ssl | mongoDB 是否使用ssl | xdm.mongodb.use.ssl | 
| no.check.uri | referer 不检查的url | xdm.no.check.uri | 
| oauth2.auth.authUrl | 授权链接 | xdm.oauth2.auth.auth-url | 
| oauth2.auth.clientId | Oauth2客户端id | xdm.oauth2.auth.client-id | 
| oauth2.auth.clientSecret | Oauth2客户端秘钥 | xdm.oauth2.auth.client-secret | 
| oauth2.auth.dsTenantId | ds租户id | xdm.oauth2.auth.ds-tenant-id | 
| oauth2.auth.endPoint | Oauth2 地址 | xdm.oauth2.auth.end-point | 
| oauth2.auth.logoutUrl | Oauth2 登出地址 | xdm.oauth2.auth.logout-url | 
| oauth2.auth.redirect.url | Oauth2  跳转地址 | xdm.oauth2.auth.redirect-url | 
| oauth2.auth.redirect.url.lecang | 重定向lecang地址 | xdm.oauth2.auth.second-redirect-url | 
| oauth2.auth.tenantId | 租户id | xdm.oauth2.auth.tenant-id | 
| oauth2.auth.tokenUrl | 获取token链接 | xdm.oauth2.auth.token-url | 
| oauth2.auth.userInfoUrl | 获取userinfo链接 | xdm.oauth2.auth.user-info-url | 
| oauth2.is.redirect.lecang | oauth2是否重定向lecang | xdm.oauth2.auth.second-redirect-enabled | 
| oauth2.sso.enabled | Oauth2 是否可用 | xdm.oauth2.sso.enabled | 
| obs.buckName | obs桶 | xdm.obs.bucket-name | 
| obs.design.buckName | obs设计态桶 | xdm.runtime.obs.design.bucket-name | 
| obs.endPoint | obs 地址 | xdm.obs.endpoint | 
| obs.init | obs 是否初始化 | xdm.obs.init | 
| OBS_TEMP_LINK_EXPIRE_TIME | OBS 临时链接到期时间 | xdm.runtime.obs.temp-link.expire-time | 
| office.url |  | xdm.runtime.office.url | 
| old.application.id | 老应用id | xdm.runtime.old.application.id | 
| orgId.enabled | orgid 是否可用 | xdm.runtime.orgid.enabled | 
| rdm.allMiddleWare.enabled | rdm 所有中间件均已启用 | xdm.all-middle-ware.enabled | 
| rdm.dbType | 数据库类型 | xdm.runtime.db-type | 
| rdm.downloadTmpPath | 下载临时目录 | xdm.download.tmp-path | 
| rdm.hibernate.dialect |  | xdm.runtime.hibernate.dialect | 
| rdm.iam.account | IAM账号 | xdm.iam.account | 
| rdm.iam.token | IAM秘钥 | xdm.iam.token | 
| rdm.unitTypeTemp.downloadTmpPath | docker下载临时路径 | xdm.runtime.unit-type.temp-download-path | 
| runningState.config.consoleUrl | 管理面url | xdm.runtime.console-url | 
| runningState.config.environment | 部署环境标识 | xdm.runtime.environment | 
| runningState.config.helpDocumentUrl | 用户指南url | xdm.runtime.help-document-url | 
| runningState.config.loginUrl | 登陆地址 | xdm.runtime.login-url | 
| runningState.config.logOutUrl | 登出地址 | xdm.runtime.logout-url | 
| runningState.IAMTokenValidation | 是否开启过滤器和拦截器中的iam token校验 true 开启；false 关闭 | xdm.runtime.iam-token.validation | 
| runningState.secondLevelCache | 是否开启运行态二级缓存 | xdm.runtime.second-level-cache.enable | 
| runningState.swagger | 是否开启运行态Swagger | xdm.runtime.swagger.enable | 
| runningState.tenantChoice | 是否开启运行态租户可选 true 开启；false 关闭 | xdm.runtime.tenant.choice | 
| runningState.xdm.function.permission | 默认关闭功能权限 | xdm.function.permission | 
| runtime.wsf.ignoreList | wsf 忽略地址 | xdm.runtime.wsf.ignore-list | 
| scheduledJobsFile.beforeDay | 删除多少天前的游离文件 | xdm.runtime.schedule-jobs.file.before-day | 
| sfs.localPath | sfs本地路径 | xdm.runtime.sfs.local-path | 
| soa.token.url |  | xdm.design.soa.token.url | 
| template.download.path.measuringunit | 运行态相关模板下载位置 | xdm.runtime.measuring-unit.template.download.path | 
| tenant.info | 默认租户信息 | xdm.runtime.tenant.info | 
| upload.bigFile.limitSize | 上传大文件限制大小，单位Byte | xdm.upload.big-file.limit-size | 
| upload.blockSize.defaultLimit | 上传块大小默认限制 | xdm.upload.block-size.default-limit | 
| upload.blockSize.maxLimit | 上传块大小最大限制 | xdm.upload.block-size.max-limit | 
| upload.single.fileSize | 单次上传大小限制，单位Byte. 100MB | xdm.upload.single.file-size | 
| xdm.custom.JsCache.enabled | js缓存是否可用 | xdm.runtime.custom.js-cache.enabled | 
| xdm.custom.JsCache.number | js缓存数量 | xdm.runtime.custom.js-cache.number | 
| xdm.custom.service.manageable | 服务编排管理可用 | xdm.runtime.custom-service.manage.enabled | 
| xdm.custom.service.modifiable | 服务编排修改可用 | xdm.runtime.custom-service.modify.enabled | 
| xdm.customService.sdk.dependency.name | 服务编排 sdk依赖名称 | xdm.runtime.custom-service.sdk.dependency.name | 
| xdm.data.compare.report.path.pattern | 数据比对报告生成路径 | xdm.runtime.data.compare.report.path.pattern | 
| xdm.dynamicDataSource.enabled | 动态数据源是否可用 | xdm.runtime.dynamic-data-source.enabled | 
| xdm.dynamicDataSource.hostWhiteList | 动态数据源白名单列表 | xdm.runtime.dynamic-data-source.host-white-list | 
| xdm.dynamicDataSource.whiteListCheckEnabled | 动态数据源白名单检查是否可用 | xdm.runtime.dynamic-data-source.white-list-check.enabled | 
| xdm.file.max.size | 文件转换 最大大小 | xdm.runtime.file.max-size | 
| xdm.file.toPdfUrl | 文件转换pdf地址 | xdm.runtime.file.to-pdf-url | 
| xdm.file.type | 文件转换类型 | xdm.runtime.file.type | 
| xdm.file.unit | 文件转换 大小单位 | xdm.runtime.file.unit | 
| xdm.index.url | 前端index地址 | xdm.runtime.index.url | 
| xdm.index.version | 前端版本 | xdm.runtime.index.version | 
| xdm.isRuntime | 判断是否为运行态，用户公共组件 com.huawei.iit.sdk.common.sso.SsoFilter 进行判断使用 | xdm.is-runtime | 
| xdm.nashorn.sandbox.maxPoolSize | NashornSandbox池 最大线程池大小 | xdm.runtime.nashorn.sandbox.max-pool-size | 
| xdm.nashorn.sandbox.poolSize | NashornSandbox池 线程池大小 | xdm.runtime.nashorn.sandbox.pool-size | 
| xdm.olc.limit-query.flow.enable | olc分页查询流控是否可用 | xdm.runtime.olc.limit-query.flow.enabled | 
| xdm.olc.limit-query.flow.rate-limit | olc分页查询流控限制率大小 | xdm.runtime.olc.limit-query.flow.rate-limit | 
| xdm.olc.limit-query.flow.time-interval | olc分页查询流控 时间间隔 | xdm.runtime.olc.limit-query.flow.time-interval | 
| xdm.olc.page.offset.maximum.value | olc分页查询流控最大偏移量值 | xdm.runtime.olc.limit-query.offset.maximum-value | 
| xdm.page.url.filter.uris | PageUrlFilter 地址 | xdm.runtime.page.url.filter.uris | 
| xdm.sdk.deploy.enable | sdk 部署是否可用 | xdm.runtime.sdk.deploy.enabled | 
| xdm.swagger.system-manager-api.filter-uris | 系统管理api接口过滤开关和接口 | xdm.runtime.swagger.system-manager-api.filter-uris | 
| xdm.system.log.download-max-count | 系统日志下载最大数量 | xdm.runtime.system.log.download-max-count | 
| xdm.system.log.enabled | 系统日志功能是否开启 | xdm.runtime.system.log.enabled | 
| xdm.tenantId | 租户id | xdm.runtime.tenant-id | 
| xdm.userCount.tempFilePath | 用户统计临时文件路径 | xdm.runtime.user-count.temp-file-path | 
| dme.operations.management.type | 告警平台类型 | xdm.operations.management.type | 
| kafka.bootstrapServers | kafka 地址 | xdm.kafka.bootstrap-servers | 
| runningState.customService.java | 是否开启java服务编排 | xdm.runtime.custom-service.java | 
| xdm.initEntityManagerFactory.enabled | 初始化实体管理工厂是否可用 | xdm.runtime.init.entity-manager-factory.enabled | 
| rdm.redis.init | redis 初始化 | xdm.redis.init | 
| cache.container | 缓存容器前缀 | xdm.cache.container | 
| application.appName | 应用名称 | xdm.application.app-name | 
| config.iam.manage.endpoint | 管理iam 地址 | xdm.iam.manage.endpoint | 
| config.token.cache.oidc.enable | oidc 是否可用 | xdm.token.cache.oidc.enable | 
| xdmf.init | 运行态是否初始化 | xdm.runtime.init | 
| xdm.config.file | xdm 配置文件是否可用 | xdm.config.file.enable | 
| file.check.whitePathList | 文件检查白名单列表 | xdm.file.check.white-path-list | 
| file.check.blackPathList | 文件检查黑名单列表 | xdm.file.check.black-path-list | 
| es.sync.enabled | es 同步是否可用 | xdm.es.sync.enable | 
| dme.search.esUserName | es 用户名 | xdm.search.es-user-name | 
| dme.search.esPassword | es密码 | xdm.search.es-password | 
| dme.search.esSyncEnable | es 同步是否可用 | xdm.search.es-sync-enable | 
| dme.search.instanceSyncEnable | es 实例同步是否可用 | xdm.search.instance-sync-enable | 
| dme.search.httpsFlag | es https 是否可用 | xdm.search.https-flag | 
| dme.config.validate.list.size | 对运行态所有接口进行校验，对列表属性进行约束的相关切面 是否可用 | xdm.runtime.validate.list.size.enable | 
| rdm.swagger | swagger 是否可用 | xdm.runtime.swagger.enable | 
| dme.fullSync.support-sync-now | linkx 全量同步 是否立即同步 | xdm.runtime.linkx.full-sync.support-sync-now | 
| scheduleJobsFile.cron | 删除未使用的文件 定时任务cron | xdm.runtime.schedule-jobs.file.cron | 
| scheduleJobsUserInfo.cron | 统计活跃用户 定时任务cron | xdm.runtime.schedule-jobs.user-info.cron | 
| scheduleJobsRwUserList.cron | 统计运行读写用户信息 定时任务cron | xdm.runtime.schedule-jobs.rw-user-list.cron | 
| xdm.custom.code.whiteEnable | xdm服务编排白名单 是否可用 | xdm.runtime.custom.code.white-enable | 
| xdm.custom.code.whitelist | xdm服务编排白名单 | xdm.runtime.custom.code.whitelist | 
| xdm.custom.code.blackEnable | xdm服务编排黑名单 是否可用 | xdm.runtime.custom.code.black-enable | 
| xdm.custom.code.blacklist | xdm服务编排黑名单 | xdm.runtime.custom.code.blacklist | 
| cors.origins | 跨域源地址 | xdm.cors.origins | 
| lifecycle.template.statesLimit | 生命周期模板阶段信息，状态数量限制 | xdm.runtime.lifecycle.template.states-limit | 
| lifecycle.template.bizOperationsLimit | 生命周期模板阶段信息，业务操作数量上限 | xdm.runtime.lifecycle.template.biz-operations-limit | 
| datakanban.job.scheduled.time | 数据看板 定时任务 cron | xdm.runtime.data-kanban.scheduled-job.cron | 
| xdm.custom.page-size | 运行态全局分页大小 | xdm.runtime.custom.page-size | 
| xdm.obs.polling.cron | obs 轮询 定时任务cron | xdm.runtime.obs.polling.cron | 
| OBS_TEMP_LINK_DOWNLOAD_EXPIRE_TIME | obs 下载临时链接失效时间 | xdm.runtime.obs.temp-link.download-expire-time | 
| java.custom.quantity.limit | java服务编排发布数量上限 | xdm.runtime.custom.quantity.limit | 
| confidential.log.audit.enabled | 是否为SM环境 | xdm.confidential.log.audit.enabled | 
| confidential.secret-level.config | 配置文件JSON对象 | xdm.confidential.secret-level.config | 
| S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL | xml  xslt | xdm.runtime.xml.extensions-universal | 
| dme.validate.float-as-int | 整型属性应校验带小数数值入参 | xdm.runtime.validate.float-as-int | 
| xdm.model3d-search.enable | 3d模型搜索可用 | xdm.runtime.model3d-search.enable | 
| xdm.model3d-search.process-service-url |  解析3d模型文件服务地址 | xdm.runtime.model3d-search.process-service-url | 
| xdm.model3d-search.search.max-file-size | 3d模型搜索上传文件最大大小 | xdm.runtime.model3d-search.search.max-file-size | 
| xdm.model3d-search.search.file-suffix[ | 3d模型搜索文件类型 | xdm.runtime.model3d-search.search.file-suffix[ | 
| xdm.model3d-search.search.file-suffix[ | 3d模型搜索文件类型 | xdm.runtime.model3d-search.search.file-suffix[ | 
| xdm.model3d-search.search.file-suffix[ | 3d模型搜索文件类型 | xdm.runtime.model3d-search.search.file-suffix[ | 
| skip.compile.customService | 是否需要跳过编译全部编排服务接口 | xdm.runtime.custom-service.skip.compile | 
| xdm.custom.service.fullfunc | 服务编排全量功能可用 | xdm.runtime.custom-service.full-func.enabled | 
| xdm.custom.service.executable | 服务编排 执行是否可用 | xdm.runtime.custom-service.execute.enabled | 
| java.compile.lib.path | 服务编排 java编译路径 | xdm.runtime.java-compile.lib-path | 
| java.compile.class.output | 服务编排 java编译 class 输出路径 | xdm.runtime.java-compile.class-output | 
| config.file.storageType | 文件存储类型 | xdm.runtime.config.file.storage-type | 
| config.cors.corsEnable | 跨域是否可用 | xdm.cors.cors-enable | 
| config.cors.whiteList | 跨域白名单 | xdm.cors.white-list | 
| iit.semaphore.interceptor.enable | 信号量拦截器是否启用 | xdm.iit.semaphore.interceptor.enabled | 
| xdm.structured-doc.appid | 结构化文档appid | xdm.structured-doc.app-id | 
| xdm.structured-doc.token.expiration.time | 结构化文档token 失效时间 | xdm.structured-doc.token.expiration-time | 
| xdm.structured-doc.single.instance.max.number | 结构化文档单实例最大文档数量 | xdm.structured-doc.single-instance.max-number | 
| xdm.structured-doc.runtime.trial.env.id | 结构化文档体验版环境id | xdm.structured-doc.runtime.trial-env-id | 
| confidential.secret-level.enable | 公共上传文件密级校验是否可用 | xdm.confidential.secret-level.enabled | 
| kafka.init | kafka初始化 | xdm.kafka.init | 
| kafka.enableSaslPlain | kafka 是否启用 sasl plain | xdm.kafka.enable-sasl-plain | 
| kafka.username | kafka用户名 | xdm.kafka.username | 
| kafka.password | kafka密码 | xdm.kafka.password | 
| kafka.topic.sys | kafka 主题系统 | xdm.runtime.kafka.topic.sys | 
| apicenter.token | api中心token | xdm.apicenter.token | 
| common_application.subAppId | 公共服务的上下文根 | xdm.common-application.sub-app-id | 
| runningState.config.ui-engine-url | ui 引擎地址 | xdm.runtime.ui-engine-url | 

| 运行态删除的配置项： | 
|  -- |
| xdm.readOnlyEntityManagerFactory.enabled | 
| appfile_path | 
| hw.cloud.ak | 
| hw.cloud.sk | 
| kafka.topic.tag | 
| kafka.topic.batch-tag | 
| dme.search.applicationNameEn | 
| dme.search.trialTenantFlag | 
| dme.search.searchMigration | 
| dme.search.maxQuota | 
| runningState.config.app.create | 
| xdm.config.context.mock | 
| apigDefaultGroupHost | 
| xdm.batchTask.timeout | 
| dme.runtime.jdbc.param.dm | 
| dme.runtime.jdbc.param.mysql | 
| dme.runtime.jdbc.param.postgresql | 
| dme.runtime.jdbc.param.gauss | 
| obs.video.buckName | 
| config.cors.whiteList[8] | 
|  | 
| cdm.cdmClusterId | 
| cdm.cdmEndPoint | 
| cdm.init | 
| es.esConnectRequestTimeOut | 
| es.esConnectTimeOut | 
| es.esPassword | 
| es.esSchema | 
| es.esSocketTimeOut | 
| es.esUrl | 
| es.esUserName | 
| hw.cloud.projectId | 
| rds.rdsDataBase | 
| rds.rdsEnabledTLSProtocols | 
| rds.rdsHost | 
| rds.rdsPassword | 
| rds.rdsPort | 
| rds.rdsSsLMode | 
| rds.rdsUserName | 
| rds.rdsType | 
| dme.sync.linkx-service-addr | 
| dme.sync.linkx-service-port | 
| file.valid.suffix | 
| dme.user.group.level | 
----

iDME xDM-F v2.25.030、v2.25.040 版本新特性

设计态：
1. 支持跨应用进行模型参考，实现模型共享
2. 新增数据实体、关系实体、功能模型另存功能
3. 优化应用版本号生成规则，更直观易懂
4. 接口模型更名为功能模型，并支持将功能模型导出
5. 业务编码生成器支持更换绑定的业务编码属性

运行态：
1. 增强事务型任务API，提供完整的事务控制能力
2. 运行态上传大文件实例时，支持通过预签名URL直接上传OBS/S3
3. 去除冗余的“外协”、“华为”用户类型并支持用户自定义用户类型
4. 文件类型扩展属性的文件格式约束将与文件白名单联动校验
5. 支持自定义运行态是否开启页面访问及API调用权限控制
6. 新增staticsPage接口支持使用分页对数据进行统计分析
7. saveAll接口参数值置空逻辑优化，若允许为空的属性不入参，系统将自动置空
8. 数据服务API详情页的请求参数表中新增“约束限制”列，用于展示各参数的输入规则及限制

配置变更：

| 配置项                       | 配置值  | 描述                             |
|---------------------------|------|--------------------------------|
| xdm.tx-distributor.enable | true | 是否部署了事务协调器，开启v3事务控制功能需要部署事务协调器 |

----

iDME xDM-F v2.25.020 版本新特性

设计态：
1. 设计态支持下载MBM SDK
2. 支持修改反向建模连接信息
3. 优化数据模型图谱显示
4. 应用责任人支持删除所有的主服务标签与主服务分组
5. 所有部署场景/局点使用同一个部署包

运行态：
1. PG数据库支持创建前缀索引
2. 提供批量删除版本对象最新小版本接口
3. 通过接口(更新、批量更新、另存、save等所有支持分类属性更新的接口)更新数据实例分类属性值时：支持传入局部更新的属性及其属性值，系统仅更新局部数据，其余未传入的分类属性保持原状不变。
4. 内置国标单位调整
5. 外链文件上传增加支持以下字符："+&@#/%?=~_{}"
6. 分类节点支持按照树形结构删除指定层级及其所有子节点
7. 支持超大文件并行上传与下载
8. select接口支持查询数据实例时，可自定义返回分类属性的范围
9. 支持jdk17、支持springboot3.x版本

配置变更：

| 配置项                | 配置值         | 描述              |
|--------------------|-------------|-----------------|
| MAX_BIG_FILE_SIZE  | 21474836480 | 上传最大文件大小（默认20G） |
| JAVA_OPTIONS | -XX:MetaspaceSize=320m -XX:+UnlockDiagnosticVMOptions --add-opens java.base/java.io=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/javax.crypto=ALL-UNNAMED --add-opens java.base/sun.security.util=ALL-UNNAMED --add-opens java.base/sun.security.x509=ALL-UNNAMED --add-opens java.base/sun.security.pkcs=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED | jdk17启动时虚拟机启动参数需增加该配置 |
----

iDME xDM-F v1.24.060 版本新特性

设计态：
1. 基本属性、扩展属性配置中，优化长文本属性类型长度限制：最大支持524288个字符；若需要存储超大内容的文本，建议使用文件对象承载；
2. 下线使用频率较低的功能：“API维护”；
3. 针对购买SDK模式的用户，支持为应用生成、下载SDK包，配合license文件可将SDK包部署至本地通用服务器独立运行；

运行态：
1. 列表查询接口(find、list、query、select等)去除默认最大偏移量50000的限制，查询超过50000条数据后可能存在响应时长下降，建议通过筛选条件缩小查询范围以确保使用体验；
2. 分类节点中文名称、英文名称放开特殊字符校验；
3. 搜索服务支持自定义是否启用高亮显示功能，若启用，则在接口返回结果中与关键字匹配的内容前后将自动添加高亮标签；
4. 优化创建、更新等接口，针对基础属性，扩展属性的校验规格趋于统一

iDME xDM-F v1.24.040 版本新特性

1.数据建模引擎-API Explorer内容优化:
API Explorer上线xDM-F部分原子接口供用户调测，并可查看原子接口出入参等信息。

