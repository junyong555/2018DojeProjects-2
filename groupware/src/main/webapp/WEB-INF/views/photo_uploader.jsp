<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>ì ¬ì§  ì²¨ë¶ í  ê¸° :: SmartEditor2</title>
<style type="text/css">
/* NHN Web Standard 1Team JJS 120106 */
/* Common */
body, p, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, table, th, td,
   form, fieldset, legend, input, textarea, button, select {
   margin: 0;
   padding: 0
}

body, input, textarea, select, button, table {
   font-family: 'ë  ì  ', Dotum, Helvetica, sans-serif;
   font-size: 12px
}

img, fieldset {
   border: 0
}

ul, ol {
   list-style: none
}

em, address {
   font-style: normal
}

a {
   text-decoration: none
}

a:hover, a:active, a:focus {
   text-decoration: underline
}

/* Contents */
.blind {
   visibility: hidden;
   position: absolute;
   line-height: 0
}

#pop_wrap {
   width: 383px
}

#pop_header {
   height: 26px;
   padding: 14px 0 0 20px;
   border-bottom: 1px solid #ededeb;
   background: #f4f4f3
}

.pop_container {
   padding: 11px 20px 0
}

#pop_footer {
   margin: 21px 20px 0;
   padding: 10px 0 16px;
   border-top: 1px solid #e5e5e5;
   text-align: center
}

h1 {
   color: #333;
   font-size: 14px;
   letter-spacing: -1px
}

.btn_area {
   word-spacing: 2px
}

.pop_container .drag_area {
   overflow: hidden;
   overflow-y: auto;
   position: relative;
   width: 341px;
   height: 129px;
   margin-top: 4px;
   border: 1px solid #eceff2
}

.pop_container .drag_area .bg {
   display: block;
   position: absolute;
   top: 0;
   left: 0;
   width: 341px;
   height: 129px;
   background: #fdfdfd url(../../img/photoQuickPopup/bg_drag_image.png) 0 0
      no-repeat
}

.pop_container .nobg {
   background: none
}

.pop_container .bar {
   color: #e0e0e0
}

.pop_container .lst_type li {
   overflow: hidden;
   position: relative;
   padding: 7px 0 6px 8px;
   border-bottom: 1px solid #f4f4f4;
   vertical-align: top
}

.pop_container :root .lst_type li {
   padding: 6px 0 5px 8px
}

.pop_container .lst_type li span {
   float: left;
   color: #222
}

.pop_container .lst_type li em {
   float: right;
   margin-top: 1px;
   padding-right: 22px;
   color: #a1a1a1;
   font-size: 11px
}

.pop_container .lst_type li a {
   position: absolute;
   top: 6px;
   right: 5px
}

.pop_container .dsc {
   margin-top: 6px;
   color: #666;
   line-height: 18px
}

.pop_container .dsc_v1 {
   margin-top: 12px
}

.pop_container .dsc em {
   color: #13b72a
}

.pop_container2 {
   padding: 46px 60px 20px
}

.pop_container2 .dsc {
   margin-top: 6px;
   color: #666;
   line-height: 18px
}

.pop_container2 .dsc strong {
   color: #13b72a
}

.upload {
   margin: 0 4px 0 0;
   _margin: 0;
   padding: 6px 0 4px 6px;
   border: solid 1px #d5d5d5;
   color: #a1a1a1;
   font-size: 12px;
   border-right-color: #efefef;
   border-bottom-color: #efefef;
   length: 300px;
}

:root  .upload {
   padding: 6px 0 2px 6px;
}
</style>
</head>
<body>
   <div id="pop_wrap">
      <!-- header -->
      <div id="pop_header">
         <h1>ì ¬ì§  ì²¨ë¶ í  ê¸°</h1>
      </div>
      <!-- //header -->
      <!-- container -->

      <!-- [D] HTML5ì ¸ ê²½ì ° pop_container í ´ë  ì ¤ì   í  ì   HTML ì  ì ©
           ê·¸ë° ì   ê²½ì ° pop_container2 í ´ë  ì ¤ì   í  ì   HTML ì  ì ©      -->
      <div id="pop_container2" class="pop_container2">
         <!-- content -->
         <form id="editor_upimage" name="editor_upimage" action=""
            method="post" enctype="multipart/form-data" onSubmit="return false;">
            <div id="pop_content2">
               <input type="file" class="upload" id="uploadInputBox"
                  name="Filedata">
               <p class="dsc" id="info">
                  <strong>10MB</strong>ì ´í  ì   ì ´ë¯¸ì§  í  ì ¼ë§  ë ±ë¡ í   ì  
                  ì  ì µë  ë ¤.<br>(JPG, GIF, PNG, BMP)
               </p>
            </div>
         </form>
         <!-- //content -->
      </div>
      <div id="pop_container" class="pop_container" style="display: none;">
         <!-- content -->
         <div id="pop_content">
            <p class="dsc">
               <em id="imageCountTxt">0ì ¥</em>/10ì ¥ <span class="bar">|</span> <em
                  id="totalSizeTxt">0MB</em>/50MB
            </p>
            <!-- [D] ì²¨ë¶  ì ´ë¯¸ì§  ì ¬ë¶ ì   ë °ë¥¸ Class ë³ í   
                ì²¨ë¶  ì ´ë¯¸ì§ ê°  ì  ë   ê²½ì ° : emì   "bg" í ´ë  ì ¤ ì  ì © //ì²¨ë¶  ì ´ë¯¸ì§ ê°  ì  ë   ê²½ì ° : emì   "nobg" í ´ë  ì ¤ ì  ì ©   -->

            <div class="drag_area" id="drag_area">
               <ul class="lst_type">
               </ul>
               <em class="blind">ë§ ì °ì ¤ë¡  ë  ë  ê·¸í ´ì   ì ´ë¯¸ì§ ë¥¼
                  ì¶ ê° í ´ì£¼ì ¸ì  .</em><span id="guide_text" class="bg"></span>
            </div>
            <div style="display: none;" id="divImageList"></div>
            <p class="dsc dsc_v1">
               <em>í   ì ¥ë ¹ 10MB, 1í  ì   50MBê¹ ì§ , 10ê° </em>ì   ì ´ë¯¸ì§ 
               í  ì ¼ì  <br>ë ±ë¡ í   ì   ì  ì µë  ë ¤. (JPG, GIF, PNG, BMP)
            </p>
         </div>
         <!-- //content -->
      </div>

      <!-- //container -->
      <!-- footer -->
      <div id="pop_footer">
         <div class="btn_area">
            <a href="#"><img
               src="resources/SEditor/img/photoQuickPopup/btn_confirm.png"
               width="49" height="28" alt="í  ì ¸" id="btn_confirm"></a> <a
               href="#"><img
               src="resources/SEditor/img/photoQuickPopup/btn_cancel.png"
               width="48" height="28" alt="ì·¨ì  " id="btn_cancel"></a>
         </div>
      </div>
      <!-- //footer -->
   </div> 
   <script type="text/javascript"
      src="resources/SEditor/photo_uploader/popup/jindo.min.js"
      charset="utf-8"></script>
   <script type="text/javascript"
      src="resources/SEditor/photo_uploader/popup/jindo.fileuploader.js"
      charset="utf-8"></script>
   <script type="text/javascript"
      src="resources/SEditor/photo_uploader/popup/attach_photo.js?textAreaName=${textAreaName}"
      charset="utf-8">
      
   </script>
</body>
</html>