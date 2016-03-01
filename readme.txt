礼品兑换项目：
项目运用技术：Springmvc3 hibernate4 jstl jQuery MySQL
项目的基本构架：
采用mvc模式，分为三层：一controller处理view提交的数据，二service处理业务性的东西，三数据访问层对数据进行持久化操作。
在service和数据访问层提供两层接口，第一层是/Gift/src/com/web/dao/ObjDAO.java拥有所有模块最基本的增删改查方法，
对于第二层接口就是针对模块对应的提供接口，接口提供特殊的业务方法。
123