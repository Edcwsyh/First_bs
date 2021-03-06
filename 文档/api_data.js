define({ "api": [
  {
    "type": "post",
    "url": "/TeacherHelper/admin/user_add",
    "title": "新增用户",
    "version": "1.0.0",
    "group": "Admin",
    "name": "新增用户",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "username",
            "description": "<p>用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>密码, 长度位6至64位</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "defaultValue": "未知",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mail",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "phone",
            "description": "<p>电话号码，长度为11位的中国电话号码</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "defaultValue": "0",
            "description": "<p>性别， 0表示不填写性别，1表示男，2表示女</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "permission",
            "description": "<p>权限, 0表示管理员, 1表示普通用户, 2表示经理</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>图片验证码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"user\"\n\t\t{\n\t\t\t\"username\":\"supper man\",\n\t\t\t\"password\":\"1234567\",\n\t\t\t\"realname\":\"张三\",\n\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\"phone\":\"13960241683\",\n\t\t\t\"gender\":1,\n\t\t\t\"permission\":1\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 该接口只允许管理员调用</p>",
    "filename": "input/AdminController.java",
    "groupTitle": "Admin"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/admin/user_add",
    "title": "新增用户",
    "version": "1.0.0",
    "group": "Admin",
    "name": "新增用户",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>被删除用户的id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"userId\" : 778899\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 该接口只允许管理员调用</p>",
    "filename": "input/AdminController.java",
    "groupTitle": "Admin"
  },
  {
    "type": "post",
    "url": "/admin/user_list",
    "title": "获取用户列表",
    "version": "1.0.0",
    "group": "Admin",
    "name": "获取用户列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\"user\" : \n     \t\t{\n     \t\t\"id\" : 8977\n\t\t\t\t\"username\":\"supper man\",\n\t\t\t\t\"password\":\"1234567\",\n\t\t\t\t\"realname\":\"张三\",\n\t\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\t\"phone\":\"13960241683\",\n\t\t\t\t\"gender\":1,\n\t\t\t\t\"permission\":1\n     \t\t},\n     \t\"user\":\n     \t\t{\n     \t\t\"id\" : 8978\n     \t\t\"username\":\"supper woman\",\n\t\t\t\t\"password\":\"1234567\",\n\t\t\t\t\"realname\":\"李四\",\n\t\t\t\t\"mail\":\"12345677@gmail.com\",\n\t\t\t\t\"phone\":\"15960241683\",\n\t\t\t\t\"gender\":1,\n\t\t\t\t\"permission\":2\n     \t\t}\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/AdminController.java",
    "groupTitle": "Admin"
  },
  {
    "type": "post",
    "url": "/admin/user_all_info",
    "title": "获取用户的所有信息",
    "version": "1.0.0",
    "group": "Admin",
    "name": "获取用户列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\t{\n     \t\t\"id\" : 8977\n\t\t\t\t\"username\":\"supper man\",\n\t\t\t\t\"password\":\"1234567\",\n\t\t\t\t\"realname\":\"张三\",\n\t\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\t\"phone\":\"13960241683\",\n\t\t\t\t\"gender\":1,\n\t\t\t\t\"permission\":1,\n\t\t\t\t\"state\" : 1\n     \t\t}\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/AdminController.java",
    "groupTitle": "Admin"
  },
  {
    "type": "post",
    "url": "/admin/user_all_info",
    "title": "获取用户的所有信息",
    "version": "1.0.0",
    "group": "Admin",
    "name": "获取用户列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\t{\n     \t\t\"id\" : 8977\n\t\t\t\t\"username\":\"supper man\",\n\t\t\t\t\"password\":\"1234567\",\n\t\t\t\t\"realname\":\"张三\",\n\t\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\t\"phone\":\"13960241683\",\n\t\t\t\t\"gender\":1,\n\t\t\t\t\"permission\":1,\n\t\t\t\t\"state\" : 1\n     \t\t}\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/AdminController.java",
    "groupTitle": "Admin"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/course/course_delete_single",
    "title": "删除单次课程",
    "version": "1.0.0",
    "group": "Course",
    "name": "删除单次课程",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "courseId",
            "description": "<p>教学目标</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\tcourseId: \"2020\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/CourseController.java",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/course/course_delete_single",
    "title": "删除多次课程",
    "version": "1.0.0",
    "group": "Course",
    "name": "删除多次课程",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "list",
            "optional": false,
            "field": "courseIdList",
            "description": "<p>课程id列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"courseIdList\" : { 11,12,13 }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/CourseController.java",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/course/course_add",
    "title": "新增课程信息",
    "version": "1.0.0",
    "group": "Course",
    "name": "新增课程信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "list",
            "optional": false,
            "field": "courseList",
            "description": "<p>课程列表</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "Course.goal",
            "description": "<p>教学目标</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "Course.content",
            "description": "<p>教学内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "Course.mode",
            "description": "<p>教学模式</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "Course.specificTime",
            "description": "<p>具体上课时间(年-月-日)</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "Course.isHomework",
            "description": "<p>是否布置作业</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"courseList\" : \n\t\t{\n\t\t\"goal\" : \"没有目标\",\n\t\t\"content\" : \"没有内容\",\n\t\t\"mode\" : 1,\n\t\t\"time\" : 2020-9-10,\n\t\t\"isHomework\" : false\n\t\t},\n \t\t{\n\t\t\"goal\" : \"有目标\",\n\t\t\"content\" : \"有内容\",\n\t\t\"mode\" : 0,\n\t\t\"time\" : 2020-9-9,\n\t\t\"isHomework\" : true\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 前端应该传入一个List<Course>列表</p>",
    "filename": "input/CourseController.java",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/course/course_update",
    "title": "更新课程信息",
    "version": "1.0.0",
    "group": "Course",
    "name": "更新课程信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "list",
            "optional": false,
            "field": "courseList",
            "description": "<p>课程列表</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "Course.id",
            "description": "<p>单次课程id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "Course.goal",
            "description": "<p>教学目标</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "Course.content",
            "description": "<p>教学内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "Course.mode",
            "description": "<p>教学模式</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "Course.specificTime",
            "description": "<p>具体上课时间(年-月-日)</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "Course.isHomework",
            "description": "<p>是否布置作业</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"courseList\" : \n\t\t{\n\t\t\"id\" : 1,\n\t\t\"goal\" : \"没有目标\",\n\t\t\"content\" : \"没有内容\",\n\t\t\"mode\" : 1,\n\t\t\"time\" : 2020-9-10,\n\t\t\"isHomework\" : false\n\t\t},\n \t\t{\n\t\t\"id\" : 2,\n\t\t\"goal\" : \"有目标\",\n\t\t\"content\" : \"有内容\",\n\t\t\"mode\" : 0,\n\t\t\"time\" : 2020-9-9,\n\t\t\"isHomework\" : true\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 前端应该传入一个List<Course>列表</p>",
    "filename": "input/CourseController.java",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/course/course_list",
    "title": "获取课程列表",
    "version": "1.0.0",
    "group": "Course",
    "name": "获取课程列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "CourseVO.goal",
            "description": "<p>教学目标</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "CourseVO.content",
            "description": "<p>教学内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "CourseVO.mode",
            "description": "<p>教学模式, 0表示学练-线下， 1表示授课-现场</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "CourseVO.specificTime",
            "description": "<p>具体上课时间(年-月-日)</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "CourseVO.isHomework",
            "description": "<p>是否布置作业</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "CourseVO.howTime",
            "description": "<p>第几大节上课</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "CourseVO.week",
            "description": "<p>周几上课</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t{\n\t\"goal\" : \"没有目标\",\n\t\"content\" : \"没有内容\",\n\t\"mode\" : 1,\n\t\"time\" : 2020-9-10,\n\t\"isHomework\" : false,\n\t\"week\" : 5,\n\t\"howTime\" : 6\n\t},\n \t{\n\t\"goal\" : \"有目标\",\n\t\"content\" : \"有内容\",\n\t\"mode\" : 0,\n\t\"time\" : 2020-9-9,\n\t\"isHomework\" : true\n \t\"week\" : 4,\n\t\"howTime\" : 3\n\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/CourseController.java",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/login/retrieve_password",
    "title": "忘记密码",
    "version": "1.0.0",
    "group": "Login",
    "name": "忘记密码",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t\t\"result\" : \n\t\t{\n\t\t\t{\n    \t\t\"Success\" : true,\n     \t\"code\" : 20000,\n     \t\"message\" : \"请求成功\",\n     \t\"data\" : true\n    \t\t}\n    }",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "value",
            "description": "<p>, 该字段可以是用户名也可以是邮箱</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\"value\" : \"supper man\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/LoginController.java",
    "groupTitle": "Login"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/login/login_check",
    "title": "检测用户",
    "version": "1.0.0",
    "group": "Login",
    "name": "检测用户",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "object",
            "optional": false,
            "field": "null",
            "description": "<p>， 该接口不返回数据， 请通过接口的错误信息判断用户是否重复</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t\t\"result\" : \n\t\t{\n\t\t\t{\n    \t\t\"Success\" : true,\n     \t\"code\" : 20000,\n     \t\"message\" : \"请求成功\",\n     \t\"data\" : null\n    \t\t}\n    \t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "field",
            "description": "<p>检测字段, 0表示用户名, 1表示邮箱, 2表示电话号码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "value",
            "description": ""
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\"field\" : 0,\n\"value\" : \"supperman\"\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口返回的类型 : 成功(不存在重复信息)、用户已存在、错误的参数</p>",
    "filename": "input/LoginController.java",
    "groupTitle": "Login"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/login/login",
    "title": "用户登录",
    "version": "1.0.0",
    "group": "Login",
    "name": "用户登录",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>登录的用户信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    \t\"result\":\n    \t{\n    \t\t{\n    \t\t\"success\" : true,\n     \t\"code\" : 20000,\n     \t\"message\" : \"请求成功\",\n     \t\"data\" : null\n    \t\t}\n \t},\n \t\"user\" : \n \t{\n \t\t\"username\":\"supper man\",\n\t\t\t\"password\":\"1234567\",\n\t\t\t\"realname\":\"张三\",\n\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\"phone\":\"13960241683\",\n\t\t\t\"gender\":1,\n\t\t\t\"permission\":1  \n \t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "username",
            "description": "<p>用户名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求样示例:",
          "content": "{\n\"username\":\"supper man\",\n\"password\":\"1234567\",\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/LoginController.java",
    "groupTitle": "Login"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/login/logout",
    "title": "用户退出登录",
    "version": "1.0.0",
    "group": "Login",
    "name": "用户退出",
    "filename": "input/LoginController.java",
    "groupTitle": "Login"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/login/goto_forgot",
    "title": "跳转至找回密码页面",
    "version": "1.0.0",
    "group": "Page",
    "name": "跳转至忘记密码",
    "description": "<p>接口说明. 该接口没有参数，也没有返回数据,仅进行页面切换</p>",
    "filename": "input/LoginController.java",
    "groupTitle": "Page"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/report/report_update",
    "title": "跳转至更新周报",
    "version": "1.0.0",
    "group": "Page",
    "name": "跳转至更新周报",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报id</p>"
          }
        ]
      }
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Page"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/login/goto_register",
    "title": "跳转至注册页面",
    "version": "1.0.0",
    "group": "Page",
    "name": "跳转至注册页面",
    "description": "<p>接口说明. 该接口没有参数，也没有返回数据,仅进行页面切换</p>",
    "filename": "input/LoginController.java",
    "groupTitle": "Page"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/login/goto_login",
    "title": "跳转至登录页面",
    "version": "1.0.0",
    "group": "Page",
    "name": "跳转至登录页面",
    "description": "<p>接口说明. 该接口没有参数，也没有返回数据,仅进行页面切换</p>",
    "filename": "input/LoginController.java",
    "groupTitle": "Page"
  },
  {
    "type": "get",
    "url": "/subject/goto_create",
    "title": "跳转至新增课表页面",
    "version": "1.0.0",
    "group": "Page",
    "name": "跳转至科目创建页面",
    "description": "<p>接口说明. 该接口没有参数，也没有返回数据,仅进行页面切换</p>",
    "filename": "input/SubjectController.java",
    "groupTitle": "Page"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/report_delete",
    "title": "删除周报",
    "version": "1.0.0",
    "group": "Report",
    "name": "删除周报",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报的id</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "author",
            "description": "<p>周报作者的id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>周报的内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "time",
            "description": "<p>周报的创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\"id\" : 8977\n\t\t\t\"author\": 1234,\n\t\t\t\"content\":\"没有内容\",\n\t\t\t\"time\":\"2020-9-1\",\n\t\t\t\"isSubmit\":true\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/report/report_add",
    "title": "新增周报",
    "version": "1.0.0",
    "group": "Report",
    "name": "新增周报",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "week",
            "description": "<p>周报所属周</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>周报内容</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "isSubmit",
            "description": "<p>是否提交, 若为true,则创建的周报将被设为只读的提交状态, 若为false,则存为草稿</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"user\"\n\t\t{\n\t\t\t\"week\" : 16,\n\t\t\t\"content\":\"abcdefg\",\n\t\t\t\"isSubmit\":true\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/report/report_update",
    "title": "更新周报",
    "version": "1.0.0",
    "group": "Report",
    "name": "更新周报",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "week",
            "description": "<p>周报所属周数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>周报内容</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "isSubmit",
            "description": "<p>是否提交, 若为true,则创建的周报将被设为只读的提交状态, 若为false,则存为草稿</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"user\"\n\t\t{\n\t\t\t\"author\":1234,\n\t\t\t\"content\":\"abcdefg\",\n\t\t\t\"isSubmit\":true\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/report_info",
    "title": "获取周报详细信息",
    "version": "1.0.0",
    "group": "Report",
    "name": "获取周报信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报的id</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "author",
            "description": "<p>周报作者的id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>周报的内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "time",
            "description": "<p>周报的创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\"id\" : 8977\n\t\t\t\"author\": 1234,\n\t\t\t\"content\":\"没有内容\",\n\t\t\t\"time\":\"2020-9-1\",\n\t\t\t\"isSubmit\":true\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/submit_report_list",
    "title": "获取所有用户的周报列表",
    "version": "1.0.0",
    "group": "Report",
    "name": "获取所有用户的周报",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报的id</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "author",
            "description": "<p>周报作者的id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>周报的内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "time",
            "description": "<p>周报的创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\t{\n     \t\t\"id\" : 8977\n\t\t\t\t\"author\": 1234,\n\t\t\t\t\"content\":\"没有内容\",\n\t\t\t\t\"time\":\"2020-9-1\",\n\t\t\t\t\"isSubmit\":true\n     \t\t},\n     \t\t{\n     \t\t\"id\" : 8978\n\t\t\t\t\"author\": 12345,\n\t\t\t\t\"content\":\"1234567\",\n\t\t\t\t\"time\":\"2020-9-2\",\n\t\t\t\t\t\"isSubmit\":true\n     \t\t}\n     \t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/report/user_report_list",
    "title": "获取某一用户的所有周报",
    "version": "1.0.0",
    "group": "Report",
    "name": "获取用户周报列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>周报的id</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "author",
            "description": "<p>周报作者的id</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>周报的内容</p>"
          },
          {
            "group": "Success 200",
            "type": "Timestamp",
            "optional": false,
            "field": "time",
            "description": "<p>周报的创建时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"result\"\n    \t\t\t{\n    \t     \t\"Success\" : true,\n     \t\t\"code\" : 20000,\n     \t\t\"message\" : \"请求成功\",\n     \t\t\"data\" : \n     \t\t\t{\n     \t\t\t\t{\n     \t\t\t\t\"id\" : 8977\n     \t\t\t\t\"week\" : 1,\n\t\t\t\t\t\t\"author\": 1234,\n\t\t\t\t\t\t\"content\":\"没有内容\",\n\t\t\t\t\t\t\"time\":\"2020-9-1\",\n\t\t\t\t\t\t\"isSubmit\":true\n     \t\t\t\t},\n     \t\t\t\t{\n     \t\t\t\t\"id\" : 8978\n     \t\t\t\t\"week\" : 2,\n\t\t\t\t\t\t\"author\": 1234,\n\t\t\t\t\t\t\"content\":\"1234567\",\n\t\t\t\t\t\t\"time\":\"2020-9-2\",\n\t\t\t\t\t\t\t\"isSubmit\":true\n     \t\t\t\t}\n     \t\t\t}\n    \t\t\t}\n    }",
          "type": "json"
        }
      ]
    },
    "filename": "input/ReportController.java",
    "groupTitle": "Report"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/subject_delete",
    "title": "删除某一科目",
    "version": "1.0.0",
    "group": "Subject",
    "name": "删除某一科目",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "subjectId",
            "description": "<p>科目id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"subjectId\":\"1167\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/SubjectController.java",
    "groupTitle": "Subject"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/subject_add",
    "title": "新增科目",
    "version": "1.0.0",
    "group": "Subject",
    "name": "新增科目",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>该科目的名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>该科目的课程类型, 0表示方向课, 1表示理论课</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "teacher",
            "description": "<p>该科目的教师id, 即登录用户的id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ta",
            "description": "<p>该科目的助教</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "klass",
            "description": "<p>该科目的班级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timeTotal",
            "description": "<p>该科目的总课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timeTheory",
            "description": "<p>该科目的理论课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timePractice",
            "description": "<p>该科目的实践课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "term",
            "description": "<p>学期的id, 默认当前学期</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"name\":\"如何对金钱失去兴趣\",\n\t\"type\":0,\n\t\"teacher\" 1,\n\t\"ta\":\"马云\",\n\t\"klass\":\"内卷学1班\",\n\t\"timeTotal\":\"66\",\n\t\"timeTheory\":\"33\",\n\t\"timePractice\":\"33\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/SubjectController.java",
    "groupTitle": "Subject"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/subject_update",
    "title": "更新科目信息",
    "version": "1.0.0",
    "group": "Subject",
    "name": "更新科目信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>该科目的名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>该科目的课程类型, 0表示方向课, 1表示理论课</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ta",
            "description": "<p>该科目的助教</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "klass",
            "description": "<p>该科目的班级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timeTotal",
            "description": "<p>该科目的总课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timeTheory",
            "description": "<p>该科目的理论课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "timePractice",
            "description": "<p>该科目的实践课时</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>科目名</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"name\":\"拾金不昧\",\n\t\"type\":0,\n\t\"ta\":\"窦尔敦\",\n\t\"klass\":\"不知道取什么名字\",\n\t\"timeTotal\":\"66\",\n\t\"timeTheory\":\"33\",\n\t\"timePractice\":\"33\"\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/SubjectController.java",
    "groupTitle": "Subject"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/subject/subject_info",
    "title": "获取科目信息",
    "version": "1.0.0",
    "group": "Subject",
    "name": "获取科目信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>该科目的id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>该科目的名称</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>该科目的课程类型, 0表示方向课, 1表示理论课</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "teacher",
            "description": "<p>讲师外键</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "ta",
            "description": "<p>该科目的助教</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "klass",
            "description": "<p>该科目的班级</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timeTotal",
            "description": "<p>该科目的总课时</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timeTheory",
            "description": "<p>该科目的理论课时</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timePractice",
            "description": "<p>该科目的实践课时</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "term",
            "description": "<p>学期外键</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     {\n     \"id\":778899,\n\t\t\"name\":\"摸鱼学导论\",\n\t\t\"type\":\"理论课\",\n\t\t\"teacher\":\"马云\"\n\t\t\"ta\":\"刘备\",\n\t\t\"klass\":\"草鞋营销1班\",\n\t\t\"timeTotal\":\"66\",\n\t\t\"timeTheory\":\"33\",\n\t\t\"timePractice\":\"33\",\n\t\t\"term\" : 2233\n     }\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "subjectId",
            "description": "<p>科目id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"subjectId\":778899,\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/SubjectController.java",
    "groupTitle": "Subject"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/subject_list",
    "title": "获取科目列表",
    "version": "1.0.0",
    "group": "Subject",
    "name": "获取科目列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>该科目的id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>该科目的名称</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>该科目的课程类型, 0表示方向课, 1表示理论课</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "ta",
            "description": "<p>该科目的助教</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "klass",
            "description": "<p>该科目的班级</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timeTotal",
            "description": "<p>该科目的总课时</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timeTheory",
            "description": "<p>该科目的理论课时</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "timePractice",
            "description": "<p>该科目的实践课时</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     {\n     \t\"id\":1778899,\n\t\t\"name\":\"菊花鉴赏\",\n\t\t\"type\":\"方向课\",\n\t\t\"ta\":\"陶渊明\",\n\t\t\"klass\":\"植物栽培1班\",\n\t\t\"timeTotal\":\"66\",\n\t\t\"timeTheory\":\"33\",\n\t\t\"timePractice\":\"33\",\n\t\t\"term\":1221\n     },\n  \t{\n     \t\"id\":2778899,\n\t\t\"name\":\"植发与护理\",\n\t\t\"type\":\"方向课\",\n\t\t\"ta\":\"蒋介石\",\n\t\t\"klass\":\"头发护理学333班\",\n\t\t\"timeTotal\":\"66\",\n\t\t\"timeTheory\":\"33\",\n\t\t\"timePractice\":\"33\",\n\t\t\"term\":1221\n     }\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "termId",
            "defaultValue": "nullptr",
            "description": "<p>学期Id, 若不指定则表示当前学期</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>用户Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"termId\" : null,\n\t\"userId\" : 1123\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/SubjectController.java",
    "groupTitle": "Subject"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/term/term_add",
    "title": "创建一个新的学期",
    "version": "1.0.0",
    "group": "Term",
    "name": "创建一个新的学期",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据，该接口不返回任何数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功示例:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \"null\"\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>学期名</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isCurrent",
            "description": "<p>学期状态，1表示激活，0表示不激活，若指定为激活，将设置新学期为当前学期</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"termName\" : \"2099年上半年秋季第12345学期\",\n\t\"isCurrent\" : 1\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口只允许管理员调用</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/term/term_delete",
    "title": "删除一个学期",
    "version": "1.0.0",
    "group": "Term",
    "name": "删除学期",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据，该接口不返回任何数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功示例:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \"null\"\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "termId",
            "description": "<p>学期的id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"termId\" : 学期id\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口只允许管理员调用</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/term/term_update",
    "title": "更新某一学期的信息",
    "version": "1.0.0",
    "group": "Term",
    "name": "更新学期",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据，该接口不返回任何数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功示例:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \"null\"\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>学期的id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>学期名</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "weeks",
            "description": "<p>周数</p>"
          },
          {
            "group": "Parameter",
            "type": "Date",
            "optional": false,
            "field": "startTime",
            "description": "<p>学期开始时间</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"id\" : 172,\n\t\"name\" : \"2099年上半年秋季第12345学期\",\n\t\"weeks\" : 20,\n\t\"startTime\" : 2019-02-01,\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口只允许管理员调用</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/term/term_activate",
    "title": "激活某一学期",
    "version": "1.0.0",
    "group": "Term",
    "name": "激活某一学期",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据，该接口不返回任何数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \"null\"\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "termId",
            "description": "<p>学期id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"termId\" : 19921\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口只允许管理员调用, 该接口调用成功后，原先激活的学期将失效</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "get",
    "url": "/term/term_list",
    "title": "获取当前学期列表",
    "version": "1.0.0",
    "group": "Term",
    "name": "获取学期列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>学期的id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>学期的的名称</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "state",
            "description": "<p>学期的状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\t\"id\" : \"123456\",\n     \t\t\"name\" : \"2099年上半年秋季第12345学期\",\n     \t\t\"state\" : 1\n     \t}\n\t}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口没有参数，但只允许登录后调用</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "get",
    "url": "/term/term_current",
    "title": "获取当前学期信息",
    "version": "1.0.0",
    "group": "Term",
    "name": "获取当前学期id",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>学期的id</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>学期的的名称</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "state",
            "description": "<p>学期的状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\t\"id\" : \"123456\",\n     \t\t\"name\" : \"2099年上半年秋季第12345学期\",\n     \t\t\"state\" : 1\n     \t}\n\t}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口没有参数，但只允许登录后调用 学期id缓存在后端，若需频繁获取学期信息，应当使用本接口</p>",
    "filename": "input/TermController.java",
    "groupTitle": "Term"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/time/time_update",
    "title": "更新时间表",
    "version": "1.0.0",
    "group": "Time",
    "name": "更新时间表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : null\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "object",
            "optional": false,
            "field": "timeVOList",
            "description": "<p>时间列表</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.id",
            "description": "<p>要被更新时间表的id(非必填, 若为空, 则表示该时间段是新增的)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.startWeek",
            "description": "<p>开始周(必填)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.endWeek",
            "description": "<p>结束周(必填)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.interval",
            "description": "<p>表示每几周上一次课(必填, 这是一个下选择下拉框, 默认为1)</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "TimeVO.addWeek",
            "description": "<p>在开始周与结束周之外还要上课的周(非必填), 格式:为一段数字序列,每个数字之间用逗号隔开,如&quot;13,15&quot;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "TimeVO.deleteWeek",
            "description": "<p>在开始周与结束周之中不需要上课的周(非必填), 格式同addWeek</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.week",
            "description": "<p>该课程在周几上课(必填, 这应该是一个选择下拉框, 范围1-7)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "TimeVO.howTime",
            "description": "<p>该课程在一天中的第几大节(必填, 这应该是一个选择下拉框, 范围1-6)</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"timeVOList\" :\n\t\t{\n\t\t\t\"id\" : null,\n\t\t\t\"startWeek\" : 1,\n\t\t\t\"endWeek\" : 12,\n\t\t\t\"interval\" : 1,\n\t\t\t\"addWeek\" : null,\n\t\t\t\"deleteWeek\" : \"4,6\",\n\t\t\t\"week\" : 4,\n\t\t\t\"howTime\" : 3\n\t\t\t//参数说明, 该参数表示第1周到第12周的每周4的第3大节(5,6小节)上课, 但是第4周和第6周不用上课(deleteWeek)\n\t\t\t//由于id为空, 表示这是一条新增数据\n\t\t},\n\t\t{\n\t\t\t\"id\" : 112,\n\t\t\t\"startWeek\" : 2,\n\t\t\t\"endWeek\" : 8,\n\t\t\t\"interval\" : 2,\n\t\t\t\"addWeek\" : \"5,13\",\n\t\t\t\"deleteWeek\" : null,\n\t\t\t\"week\" : 1,\n\t\t\t\"howTime\" : 1\n\t\t\t//参数说明, 该参数表示第2, 4, 5(addWeek), 6, 8, 13周每周1的第1大节(1,2小节)上课\n\t\t\t//由于id为非空, 将会更新该id所指向的时间段以及引用该时间段的课程的时间\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 前端应该传入一个List<TimeVO>列表</p>",
    "filename": "input/TimeController.java",
    "groupTitle": "Time"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/subject/time/time_list",
    "title": "查看时间列表",
    "version": "1.0.0",
    "group": "Time",
    "name": "查看时间列表",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "list",
            "optional": false,
            "field": "data",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n\t\t\"success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" :\n\t\t\t{\n\t\t\t\t\"id\" : 112,\n\t\t\t\t\"weeks\" : \"第1周到第7周, 第9周到第11周\"\n\t\t\t\t\"week\" : 1,\n\t\t\t\t\"howTime\" : 1\n\t\t\t},\n\t\t\t{\n\t \t\t\t\"id\" : 113,\n\t\t\t\t\"weeks\" : \"第1周到第7周, 第9周到第11周\"\n\t\t\t\t\"week\" : 2,\n\t\t\t\t\"howTime\" : 1\n\t\t\t}\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "subjectId",
            "description": "<p>需要查看的科目id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"subjectId\" : 889\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/TimeController.java",
    "groupTitle": "Time"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/user/user_update",
    "title": "修改用户",
    "version": "1.0.0",
    "group": "User",
    "name": "修改用户信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "username",
            "description": "<p>用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>密码, 长度位6至64位</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realname",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mail",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "phone",
            "description": "<p>电话号码，长度为11位的中国电话号码</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>性别， 0表示不填写性别，1表示男，2表示女</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "permission",
            "defaultValue": "当前值",
            "description": "<p>用户权限, 0表示管理员, 1表示普通用户, 2表示经理</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "requestId",
            "description": "<p>提出请求的用户id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\trequestId : 75546\n\tdata:\n\t\t{\n\t\t\t\"username\":\"supper man\",\n\t\t\t\"password\":\"1234567\",\n\t\t\t\"realname\":\"张三\",\n\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\"phone\":\"13960241683\",\n\t\t\t\"gender\":1,\n\t\t\t\"permission\":1,\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 该接口只更新用户信息, 不更新用户密码, 权限, 状态</p>",
    "filename": "input/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/user/user_update_password",
    "title": "修改用户密码",
    "version": "1.0.0",
    "group": "User",
    "name": "修改用户信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "oldPassword",
            "description": "<p>用户旧密码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "newPassword",
            "description": "<p>用户新密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"oldPassword\" : 123456,\n\t\"newPassword\" : 9921312\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 该接口只更新用户密码</p>",
    "filename": "input/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/user/user_register",
    "title": "注册用户",
    "version": "1.0.0",
    "group": "User",
    "name": "注册用户",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"Success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "username",
            "description": "<p>用户名, 长度为3-16位, 只能由字母、数字及下划线组成，且首字符不能为数字</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>密码, 长度位6至64位</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mail",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "phone",
            "description": "<p>电话号码，长度为11位的中国电话号码</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>性别， 0表示不填写性别，1表示男，2表示女</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"user\"\n\t\t{\n\t\t\t\"username\":\"supper man\",\n\t\t\t\"password\":\"1234567\",\n\t\t\t\"realname\":\"张三\",\n\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\"phone\":\"13960241683\",\n\t\t\t\"gender\":1,\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/user/user_info",
    "title": "获取用户信息",
    "version": "1.0.0",
    "group": "User",
    "name": "获取用户信息",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>获取的用户的id, 该字段仅用户本人和管理员可见</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "username",
            "description": "<p>该用户的用户名</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>该用户的密码, 该字段仅管理员及用户本人可见</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>该用户的真实姓名, 该字段仅用户本人可见</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "mail",
            "description": "<p>该用户的邮箱, 该字段仅管理员及用户本人可见</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "phone",
            "description": "<p>该用户的手机号码, 该字段仅用户本人和管理员可见</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>该用户的性别, 0表示不透露性别, 1表示男性, 2表示女性</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "permission",
            "description": "<p>该用户的权限, 该字段仅用户本人管理员可见, 0表示管理员, 1表示普通用户, 2表示经理</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "state",
            "description": "<p>该用户的状态, 该字段仅用户本人管理员可见, 0表示被封禁, 1表示状态正常,</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "    {\n    \t\"result\":\n    \t\t{\n    \t\t\"success\" : true,\n     \t\"code\" : 20000,\n     \t\"message\" : \"请求成功\",\n     \t\"data\" : \n     \t\t{\n     \t\t\t\"userId\" : 8977\n\t\t\t\t\t\"username\":\"supper man\",\n\t\t\t\t\t\"password\":\"1234567\",\n\t\t\t\t\t\"realname\":\"张三\",\n\t\t\t\t\t\"mail\":\"123456@gmail.com\",\n\t\t\t\t\t\"phone\":\"13960241683\",\n\t\t\t\t\t\"gender\":1,\n\t\t\t\t\t\"permission\":1,\n\t\t\t\t\t\"state\" : 1\n    \t\t\t}\n    \t\t}\n    }",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "requestId",
            "description": "<p>发出请求的用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "password",
            "description": "<p>发出请求的用户的密码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "id",
            "description": "<p>需要获取的用户的Id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n\t\"id\" : 998811,\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "21011",
            "description": "<p>未找到用户</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "21010",
            "description": "<p>用户密码错误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "{\n\t\"result\":\n\t\t{\n\t\t\"success\" : false,\n \t\"code\" : 21010,\n \t\"message\" : \"用户密码错误\",\n \t\"data\" : null\n\t\t}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "input/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "/TeacherHelper/term/goto_term_update",
    "title": "跳转到更新学期页面",
    "version": "1.0.0",
    "group": "page",
    "name": "更新学期页面",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "Success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回的数据，该接口不返回任何数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "\t{\n    \t\"Success\" : true,\n     \"code\" : 20000,\n     \"message\" : \"请求成功\",\n     \"data\" : \n     \t{\n     \t\"id\" : 172,\n\t\t\t\"name\" : \"2099年上半年秋季第12345学期\",\n\t\t\t\"weeks\" : 20,\n\t\t\t\"startTime\" : 2019-02-01,\n     \t}\n\t}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "termId",
            "description": "<p>学期id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\t\n\t\"termId\" : 19921\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明. 该接口只允许管理员调用,</p>",
    "filename": "input/TermController.java",
    "groupTitle": "page"
  },
  {
    "type": "post",
    "url": "/TeacherHelper/user/user_lock",
    "title": "锁定用户",
    "version": "1.0.0",
    "group": "user",
    "name": "锁定用户",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "success",
            "description": "<p>true表示请求成功，false表示请求失败</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误代码</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "message",
            "description": "<p>错误信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求成功例子:",
          "content": "{\n\t\"success\" : true,\n \"code\" : 20000,\n \"message\" : \"请求成功\",\n \"data\" : null\n}",
          "type": "json"
        }
      ]
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lockUserId",
            "description": "<p>被锁定的用户的id</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求示例:",
          "content": "{\n    \"lockUserId\":\"877661\"\n}",
          "type": "json"
        }
      ]
    },
    "description": "<p>接口说明: 用户注销账户应该调用此接口，</p>",
    "filename": "input/UserController.java",
    "groupTitle": "user"
  }
] });
