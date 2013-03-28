package com.tavenli.utils;

public class PageNavUtil {
	
	public static String getPageNavHtml(int currentPage,int pageSize,  int totalRows, int showNums){
		
		StringBuilder pageNavHtml = new StringBuilder();
		
		if (showNums < 1) { showNums = 5; }

        //计算总页数
        int totalPage = (int) Math.ceil(totalRows/Double.parseDouble(String.valueOf(pageSize)));
        
        //计算中间页码数字
        int midNum = (int)Math.ceil(Double.parseDouble(String.valueOf(showNums)) / 2);

        int beginNum = currentPage <= midNum ? 1 : currentPage - midNum + 1;

        int endNum = beginNum + showNums - 1;

        if (endNum > totalPage) { endNum = totalPage; }

        
        //至少有1页以上 才显示分页导航
        if (totalPage > 1)
        {

            //需要显示 首页
            if (currentPage > 1)
            {
                pageNavHtml.append("<a href='?page=1'> 首页</a>");
                
            }

            //如果有上一页
            if (currentPage > beginNum)
            {
				pageNavHtml.append("<a href='?page=" + (currentPage - 1) + "'> &lt; </a>");
                
            }
            else
            {
            	pageNavHtml.append("<span class='disabled'> &lt; </span>");
            }

            for (int i = beginNum; i <= endNum; i++)
            {
                if (i == currentPage)
                {
                	pageNavHtml.append("<span class='current'>" + currentPage + "</span>");
                }
                else
                {
                	pageNavHtml.append("<a href='?page=" + i + "'>" + i + "</a>");
                	
                }

            }

            //如果有下一页
            if (currentPage < endNum)
            {
            	pageNavHtml.append("<a href='?page=" + (currentPage + 1) + "'> &gt; </a>");
            	
            }
            else
            {
            	pageNavHtml.append("<span class='disabled'> &gt; </span>");
            }

            //需要显示 尾页
            if (currentPage < totalPage)
            {
            	pageNavHtml.append("<a href='?page=" + totalPage + "'>尾页</a>");
            }

        }

        
        
        return pageNavHtml.toString();
		
	}
	
	public static String getAjaxPageNavHtml(int currentPage,int pageSize,  int totalRows, int showNums){
		
		StringBuilder pageNavHtml = new StringBuilder();
		
		if (showNums < 1) { showNums = 5; }

        //计算总页数
        int totalPage = (int) Math.ceil(totalRows/Double.parseDouble(String.valueOf(pageSize)));
        
        //计算中间页码数字
        int midNum = (int)Math.ceil(Double.parseDouble(String.valueOf(showNums)) / 2);

        int beginNum = currentPage <= midNum ? 1 : currentPage - midNum + 1;

        int endNum = beginNum + showNums - 1;

        if (endNum > totalPage) { endNum = totalPage; }

        
        //至少有1页以上 才显示分页导航
        if (totalPage > 1)
        {

            //需要显示 首页
            if (currentPage > 1)
            {
                pageNavHtml.append("<a href='javascript:;' onclick='loadPageData(1,true);'> 首页</a>");
            }

            //如果有上一页
            if (currentPage > beginNum)
            {
				pageNavHtml.append("<a href='javascript:;' onclick='loadPageData(" + (currentPage - 1) + ",true);'> &lt; </a>");
            }
            else
            {
            	pageNavHtml.append("<span class='disabled'> &lt; </span>");
            }

            for (int i = beginNum; i <= endNum; i++)
            {
                if (i == currentPage)
                {
                	pageNavHtml.append("<span class='current'>" + currentPage + "</span>");
                }
                else
                {
                	pageNavHtml.append("<a href='javascript:;' onclick='loadPageData(" + i + ",true);'> " + i + "</a>");
                }

            }

            //如果有下一页
            if (currentPage < endNum)
            {
            	pageNavHtml.append("<a href='javascript:;' onclick='loadPageData(" + (currentPage + 1) + ",true);'> &gt; </a>");
            }
            else
            {
            	pageNavHtml.append("<span class='disabled'> &gt; </span>");
            }

            //需要显示 尾页
            if (currentPage < totalPage)
            {
            	pageNavHtml.append("<a href='javascript:;' onclick='loadPageData(" + totalPage + ",true);'>尾页</a>");
            }

        }

        
        
        return pageNavHtml.toString();
		
	}

}
