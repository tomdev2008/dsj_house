<#include "common/taglibs.ftl">

<div class="dsj_page_container">
	<nav aria-label="Page navigation" class="text-center">
	  <ul class="pagination">
	  
	    <#if page.pageCount gt 0 >
	    	 <#if page.pageCount lte 5 >
		    
		   
		     <#list page.beginPageIndex.. page.endPageIndex as i>    
		    <li <#if page.currentPage==i> class="active"</#if>>
		    
		    <#if keywords??>
		      				<a  href="${ctx}${url_pg}${i}?k=${keywords}">${i}</a>
		      			<#else>
		      				<a  href="${ctx}${url_pg}${i}">${i}</a>
		      			</#if>
		    </a></li>
		   </#list>
		   
		    
		    <#else>
		 		<#if page.currentPage gte 5 >
		 			<li><a href="${ctx}${url_pg}1">1</a></li>
		 			<li><a>..</a></li>
			     	<#list page.beginPageIndex.. page.endPageIndex as i>    
		      		<li <#if page.currentPage==i> class="active"</#if>>
		      			<#if keywords??>
		      				<a  href="${ctx}${url_pg}${i}?k=${keywords}">${i}</a>
		      			<#else>
		      				<a  href="${ctx}${url_pg}${i}">${i}</a>
		      			</#if>
		      		</li>
		  	 		</#list>
		  	 		
		  	 		
		  	 		<#if page.pageCount-page.currentPage gt 2 >
		  	 			<li><a>..</a></li>
		  	 			<li>
							<#if keywords??>
			      					<a  href="${ctx}${url_pg}${page.pageCount}?k=${keywords}">${page.pageCount}</a>
			      			<#else>
			      					<a  href="${ctx}${url_pg}${page.pageCount}">${page.pageCount}</a>
			      			</#if>
			      		
						</li>
		  	 	
		  	 		</#if>
		  	 		
		  	 	<#else>	
		  	 		<#list 1.. page.endPageIndex as i>    
		      		<li <#if page.currentPage==i> class="active"</#if>>
		      			<#if keywords??>
		      				<a  href="${ctx}${url_pg}${i}?k=${keywords}">${i}</a>
		      			<#else>
		      				<a  href="${ctx}${url_pg}${i}">${i}</a>
		      			</#if>
		      		</li>
					</#list>
					<li><a>..</a></li>
					<li>
					<#if keywords??>
			      					<a  href="${ctx}${url_pg}${page.pageCount}?k=${keywords}">${page.pageCount}</a>
			      			<#else>
			      					<a  href="${ctx}${url_pg}${page.pageCount}">${page.pageCount}</a>
			      			</#if>
			      	</li>
		  		</#if>
		    	
		    </#if>
	    </#if>
	    
	  </ul>
		
	</nav>
</div>