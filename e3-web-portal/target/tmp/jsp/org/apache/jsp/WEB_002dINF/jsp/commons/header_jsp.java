/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.0.v20161208
 * Generated at: 2019-04-21 14:41:40 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.commons;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!--shortcut start-->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "shortcut.jsp", out, false);
      out.write("\r\n");
      out.write("<!--shortcut end-->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("  <div class=\"header_inner\">\r\n");
      out.write("    <div class=\"logo\">\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"index_topad\" id=\"playLogo\" style=\"\">\r\n");
      out.write("            <a href=\"/html/activity/1472179566.html\" target=\"_blank\">\r\n");
      out.write("              <img src=\"/images/html/20160829181637762.gif\">\r\n");
      out.write("            </a> \r\n");
      out.write("        </div>\r\n");
      out.write("\t\t<a name=\"sfbest_hp_hp_head_logo\" href=\"http://www.e3mall.cn\" class=\"trackref logoleft\">\r\n");
      out.write("\t\t</a>\r\n");
      out.write("\t\t<div class=\"logo-text\">\r\n");
      out.write("\t\t\t<img src=\"/images/html/logo_word.jpg\">\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("    <div class=\"index_promo\"></div>\r\n");
      out.write("    <div class=\"search\">\r\n");
      out.write("      <form action=\"http://localhost:8082/search.html\" id=\"searchForm\" name=\"query\" method=\"GET\">\r\n");
      out.write("        <input type=\"text\" class=\"text keyword ac_input\" name=\"keyword\" id=\"keyword\" value=\"\" style=\"color: rgb(153, 153, 153);\" onkeydown=\"javascript:if(event.keyCode==13) search_keys('searchForm');\" autocomplete=\"off\">\r\n");
      out.write("        <input type=\"button\" value=\"\" class=\"submit\" onclick=\"search_keys('searchForm')\">\r\n");
      out.write("      </form>\r\n");
      out.write("      <div class=\"search_hot\"><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E5%A4%A7%E9%97%B8%E8%9F%B9#trackref=sfbest_hp_hp_head_Keywords1\">大闸蟹</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%9F%B3%E6%A6%B4#trackref=sfbest_hp_hp_head_Keywords2\">石榴</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%9D%BE%E8%8C%B8#trackref=sfbest_hp_hp_head_Keywords3\">松茸</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%89%9B%E6%8E%92#trackref=sfbest_hp_hp_head_Keywords4\">牛排</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%99%BD%E8%99%BE#trackref=sfbest_hp_hp_head_Keywords5\">白虾</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E5%85%A8%E8%84%82%E7%89%9B%E5%A5%B6#trackref=sfbest_hp_hp_head_Keywords6\">全脂牛奶</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E6%B4%8B%E6%B2%B3#trackref=sfbest_hp_hp_head_Keywords7\">洋河</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E7%BB%BF%E8%B1%86#trackref=sfbest_hp_hp_head_Keywords8\">绿豆</a><a target=\"_blank\" href=\"http://www.e3mall.cn/productlist/search?inputBox=1&amp;keyword=%E4%B8%80%E5%93%81%E7%8E%89#trackref=sfbest_hp_hp_head_Keywords9\">一品玉</a></div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"shopingcar\" id=\"topCart\">\r\n");
      out.write("      <s class=\"setCart\"></s><a href=\"http://localhost:8089/cart/cart.html\" class=\"t\" rel=\"nofollow\">我的购物车</a><b id=\"cartNum\">0</b>\r\n");
      out.write("      <span class=\"outline\"></span>\r\n");
      out.write("      <span class=\"blank\"></span>\r\n");
      out.write("      <div id=\"cart_lists\">\r\n");
      out.write("        <!--cartContent-->   \r\n");
      out.write("        <div class=\"clear\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
