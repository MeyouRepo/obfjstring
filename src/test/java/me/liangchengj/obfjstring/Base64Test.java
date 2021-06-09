package me.liangchengj.obfjstring;

import org.junit.jupiter.api.Test;

class Base64Test {

  @Test
  void test() {
    String string = "Liangcheng Juves";
    String url =
        "https://app.yinxiang.com/Home.action?hpts=1623212761205&loginToken=S%3Ds59%3AU%3D1af0018%3AE%3D179ef3c0ed0%3AC%3D179ef052054%3AP%3D5fd%3AA%3Den-web%3AV%3D3%3AH%3D353f4c8012028589d46afa5f6f22f0cb&wechatLogin=true&login=true&hptsh=sYzaNjv6lwop5OHZQmAJSU5LNKQ%3D#n=99938cb7-cb85-4afc-b7c9-baee2e6a377a&s=s59&ses=4&sh=2&sds=5&";

    String text =
        "\n"
            + "<!DOCTYPE html>\n"
            + "<!--[if lt IE 7 ]> <html class=\"ie6\"> <![endif]--><!--[if IE 7 ]>    <html class=\"ie7\"> <![endif]--><!--[if IE 8 ]>    <html class=\"ie8\"> <![endif]--><!--[if IE 9 ]>    <html class=\"ie9\"> <![endif]--><!--[if !IE]><!--> <html>         <!--<![endif]--><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
            + "    <meta name=\"en:locale\" content=\"zh_CN_evernoteChina\" />\n"
            + "    <meta name=\"gwt:property\" content=\"locale=zh_CN_ENCHINA\" />\n"
            + "    <meta name=\"apple-itunes-app\" content=\"app-id=281796108\" />\n"
            + "\n"
            + "    <!-- Google Analytics -->\n"
            + "<script type=\"text/javascript\">\n"
            + "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n"
            + "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n"
            + "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n"
            + "})(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n"
            + "\n"
            + "window.ga('create', 'UA-117332876-4',\n"
            + "    'auto', {});\n"
            + "window.ga('require', 'displayfeatures');\n"
            + "\n"
            + "\n"
            + "/* <![CDATA[ */\n"
            + "  \n"
            + "  window.ga('set', 'dimension6', 'FREE');\n"
            + "  \n"
            + "  window.ga('set', 'dimension7', '5811dcd1-b644-4f61-afee-b3e75a4706ab');\n"
            + "  \n"
            + "  window.ga('set', '&uid', 'YX28246040');\n"
            + "  \n"
            + "  window.ga('set', 'location', 'https://app.yinxiang.com/Home.action');\n"
            + "  \n"
            + "  window.ga('set', 'dimension5', 'YX28246040');\n"
            + "  \n"
            + "/* ]]> */\n"
            + "</script>\n"
            + "<!-- End Google Analytics -->\n"
            + "</head><body class=\"\"><noscript>\n"
            + "      没有Javascript，印象笔记网页版将无法工作。请在浏览器中启用Javascript，然后重新载入本页。</noscript>\n"
            + "\n"
            + "    <iframe id=\"__gwt_historyFrame\" style=\"width: 0; height: 0; border: 0\"></iframe>\n"
            + "\n"
            + "    <script type=\"text/javascript\">\n"
            + "      var ENConfig = {\n"
            + "        passwordChanged: false,\n"
            + "        // Escape the string to avoid harmful XSS code in the joined notebook name\n"
            + "        joinedPublicNotebookUri: \"\",\n"
            + "        joinedPublicNotebookOwner: \"\",\n"
            + "        joinedPublicNotebookGuid: \"\",\n"
            + "        relatedNotesTesting: false,\n"
            + "        ownerLoggedIn: true,\n"
            + "        corporateWebSiteBaseUrl: \"https://www.yinxiang.com/\",\n"
            + "        isChinaService: true,\n"
            + "        serviceWebSiteBaseUrl: \"https://app.yinxiang.com\",\n"
            + "        version: \"WEB2.0\",\n"
            + "        mailGatewayHostname: \"@m.yinxiang.com\",\n"
            + "        isNewUser: false,\n"
            + "        isIonAlpha: false,\n"
            + "        isIonUnsupportedBrowser: false,\n"
            + "        forcedIntoFocus: false,\n"
            + "        topAcceptLanguage: \"zh-cn\",\n"
            + "        betaOptOutMaxCreated: 1440054000000,\n"
            + "        mailGatewayUsed: false,\n"
            + "        userId: 28246040,\n"
            + "        userIdentityIds: \"[12225894]\",\n"
            + "        userNotebookGuid: \"\",\n"
            + "        notebookRenamingEnabled: true,\n"
            + "        maxUploadAttachments: 10,\n"
            + "        isRestrictEmailNote: false,\n"
            + "        isUpdateBillingEnabled: false,\n"
            + "        isRestrictCreateThread: false,\n"
            + "        isLinkedNotebookCleanupEnabled:\n"
            + "            true,\n"
            + "        commonEditorEnabled: true,\n"
            + "        ownProductionBusiness: false,\n"
            + "        googleId: \"447407681759.apps.googleusercontent.com\",\n"
            + "        googleApiKey: \"AIzaSyBcWPxEZszNiKmt2KJ5vczHSPgCl0Nn-eU\",\n"
            + "        gdEnabled: false,\n"
            + "        commonEditorWebkitRolloutPercentage:\n"
            + "            100,\n"
            + "        commEngineEnabled: true,\n"
            + "        commEngineV2: true,\n"
            + "        accountMenuDownloadLinkExpGroup: \"B_download\",\n"
            + "        fleChecklistMinCreated: 1473922800000,\n"
            + "        isBobUser: false,\n"
            + "        existingUserSecondClientIntroMaxCreated:\n"
            + "            1473300000000,\n"
            + "        isGoogleCalendarIntegrationEnabled:\n"
            + "            true,\n"
            + "        isNotebooksFleEligible: false,\n"
            + "        slotPrefix: \"\",\n"
            + "        isMultiauthAccountSwitcherEnabled: false,\n"
            + "        helpCenterBaseUrl: \"http://help.yinxiang.com/hc\",\n"
            + "        clientDownloadRedirectPath: \"/getit\",\n"
            + "        isShowMoveNotebooks: false,\n"
            + "        editorUpgradeButtonColorVariant: \"A_Control\",\n"
            + "        quotaChoicePageVariant: \"B_ChoiceScreen\",\n"
            + "        noteSizeChoicePageVariant: \"B_ChoiceScreen\",\n"
            + "        cdnUIHostPath: \"https://static.app.yinxiang.com\"\n"
            + "      };\n"
            + "\n"
            + "      \n"
            + "        ENConfig.userId = 28246040;\n"
            + "        ENConfig.websocketUrl = \"wss://ws.app.yinxiang.com/shard/s59/id\";\n"
            + "      \n"
            + "      ENConfig.focusPublicBeta = true;\n"
            + "\n"
            + "      \n"
            + "\n"
            + "      var ENBootstrap = {\n"
            + "        getUser: \"{\\\"1\\\":{\\\"i32\\\":28246040},\\\"2\\\":{\\\"str\\\":\\\"152bphfr428\\\"},\\\"3\\\":{\\\"str\\\":\\\"liangchengj@outlook.com\\\"},\\\"4\\\":{\\\"str\\\":\\\"Liangcheng Juves\\\"},\\\"7\\\":{\\\"i32\\\":1},\\\"9\\\":{\\\"i64\\\":1582425172000},\\\"10\\\":{\\\"i64\\\":1620622319000},\\\"13\\\":{\\\"tf\\\":1},\\\"14\\\":{\\\"str\\\":\\\"s59\\\"},\\\"15\\\":{\\\"rec\\\":{\\\"6\\\":{\\\"str\\\":\\\"152bphfr428.53e9f74\\\"},\\\"14\\\":{\\\"str\\\":\\\"yx-oppo-an\\\"},\\\"20\\\":{\\\"str\\\":\\\"zh_CN_evernoteChina\\\"},\\\"35\\\":{\\\"i64\\\":1620622319000},\\\"36\\\":{\\\"i64\\\":1582425172000},\\\"40\\\":{\\\"str\\\":\\\"YXBJ Android New/10.6.2_12.2020069 (zh_CN); Android/10; ONEPLUS A6010/29; EDAMVersion=V2;\\\"},\\\"43\\\":{\\\"tf\\\":0}}},\\\"16\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"i64\\\":62914560},\\\"2\\\":{\\\"i64\\\":1624406400000},\\\"3\\\":{\\\"i64\\\":62914560},\\\"4\\\":{\\\"i32\\\":0},\\\"14\\\":{\\\"i64\\\":1620622830000},\\\"19\\\":{\\\"i32\\\":0},\\\"23\\\":{\\\"i32\\\":0},\\\"27\\\":{\\\"i32\\\":0},\\\"28\\\":{\\\"i32\\\":0},\\\"29\\\":{\\\"i32\\\":0}}},\\\"17\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"i64\\\":1623212761960},\\\"2\\\":{\\\"tf\\\":0},\\\"3\\\":{\\\"tf\\\":0},\\\"5\\\":{\\\"tf\\\":0},\\\"6\\\":{\\\"tf\\\":0},\\\"7\\\":{\\\"tf\\\":0},\\\"8\\\":{\\\"tf\\\":0},\\\"11\\\":{\\\"tf\\\":1}}},\\\"19\\\":{\\\"str\\\":\\\"https://app.yinxiang.com/shard/s59/user/28246040/photo\\\"},\\\"20\\\":{\\\"i64\\\":1582425177000},\\\"21\\\":{\\\"i32\\\":1},\\\"22\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"i32\\\":50},\\\"2\\\":{\\\"i64\\\":26214400},\\\"3\\\":{\\\"i64\\\":26214400},\\\"4\\\":{\\\"i32\\\":100},\\\"5\\\":{\\\"i64\\\":62914560},\\\"6\\\":{\\\"i32\\\":100000},\\\"7\\\":{\\\"i32\\\":250},\\\"8\\\":{\\\"i32\\\":100000},\\\"9\\\":{\\\"i32\\\":100},\\\"10\\\":{\\\"i32\\\":100},\\\"11\\\":{\\\"i32\\\":1000},\\\"12\\\":{\\\"i32\\\":2},\\\"13\\\":{\\\"i32\\\":2},\\\"14\\\":{\\\"i32\\\":0},\\\"15\\\":{\\\"i64\\\":26214400},\\\"16\\\":{\\\"i64\\\":20},\\\"17\\\":{\\\"i32\\\":2147483647}}},\\\"23\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"i64\\\":1623212761960},\\\"2\\\":{\\\"tf\\\":0},\\\"3\\\":{\\\"tf\\\":0},\\\"5\\\":{\\\"tf\\\":0},\\\"6\\\":{\\\"tf\\\":0},\\\"7\\\":{\\\"set\\\":[\\\"i32\\\",3,2,5,3]},\\\"9\\\":{\\\"i64\\\":1654748761960},\\\"10\\\":{\\\"tf\\\":0},\\\"11\\\":{\\\"tf\\\":0},\\\"13\\\":{\\\"str\\\":\\\"NONE\\\"},\\\"14\\\":{\\\"tf\\\":0},\\\"15\\\":{\\\"tf\\\":0},\\\"16\\\":{\\\"tf\\\":0}}},\\\"24\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"tf\\\":1},\\\"2\\\":{\\\"tf\\\":0},\\\"3\\\":{\\\"i64\\\":1582425172000}}},\\\"25\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"str\\\":\\\"152*****428\\\"}}}}\",\n"
            + "        listLinkedNotebooks: [],\n"
            + "        listNotebooks: [\"{\\\"1\\\":{\\\"str\\\":\\\"5ec1f653-935e-4dd2-93ef-aa440c7fbbb4\\\"},\\\"2\\\":{\\\"str\\\":\\\"我的笔记本\\\"},\\\"5\\\":{\\\"i32\\\":135},\\\"6\\\":{\\\"tf\\\":1},\\\"7\\\":{\\\"i64\\\":1582425172000},\\\"8\\\":{\\\"i64\\\":1620623162000},\\\"17\\\":{\\\"rec\\\":{\\\"12\\\":{\\\"tf\\\":1},\\\"13\\\":{\\\"tf\\\":1},\\\"21\\\":{\\\"tf\\\":1},\\\"23\\\":{\\\"tf\\\":1},\\\"26\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"i32\\\":2}}},\\\"27\\\":{\\\"tf\\\":1}}},\\\"18\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"tf\\\":1},\\\"2\\\":{\\\"tf\\\":1},\\\"3\\\":{\\\"tf\\\":1},\\\"5\\\":{\\\"i32\\\":3}}},\\\"21\\\":{\\\"tf\\\":0},\\\"22\\\":{\\\"i32\\\":0}}\"],\n"
            + "        listSearches: [\"{\\\"1\\\":{\\\"str\\\":\\\"39c715c2-b1e6-47e9-bd27-bcb08b5f8c5b\\\"},\\\"2\\\":{\\\"str\\\":\\\"最近7天未完成的任务清单\\\"},\\\"3\\\":{\\\"str\\\":\\\"todo:false created:day-7\\\"},\\\"4\\\":{\\\"i32\\\":1},\\\"5\\\":{\\\"i32\\\":3},\\\"6\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"tf\\\":1},\\\"2\\\":{\\\"tf\\\":1}}}}\",\"{\\\"1\\\":{\\\"str\\\":\\\"10e0e7fe-fd38-4e0d-9982-7522247f2bdb\\\"},\\\"2\\\":{\\\"str\\\":\\\"去年的今天\\\"},\\\"3\\\":{\\\"str\\\":\\\"created:day-366 -created:day-365\\\"},\\\"4\\\":{\\\"i32\\\":1},\\\"5\\\":{\\\"i32\\\":4},\\\"6\\\":{\\\"rec\\\":{\\\"1\\\":{\\\"tf\\\":1},\\\"2\\\":{\\\"tf\\\":1}}}}\"],\n"
            + "        listSharedNotebooks: [],\n"
            + "        listTags: []\n"
            + "      };\n"
            + "\n"
            + "      </script>\n"
            + "\n"
            + "    <script type=\"text/javascript\">\n"
            + "  var ENWebFeatures = {\n"
            + "    facebookEnabled : false,\n"
            + "    twitterEnabled : false,\n"
            + "    linkedinEnabled : false,\n"
            + "    noteSharingEnabled : false,\n"
            + "    notebookSharingEnabled : true,\n"
            + "    googleEnabled : false\n"
            + "\t};\n"
            + "</script>\n"
            + "<script type=\"text/javascript\">\n"
            + "      ENWebFeatures.googleGlassEnabled = false;\n"
            + "    </script>\n"
            + "\n"
            + "    <script type=\"text/javascript\" src=\"/redesign/global/js/decrypt.js\"></script>\n"
            + "<script type=\"text/javascript\" src=\"/js/tinymce/tinymce.js\"></script>\n"
            + "<script type=\"text/javascript\" src=\"/webclient/js/flash_detect_min.js\"></script>\n"
            + "<script type=\"text/javascript\" src=\"/focusclient/focusclient.nocache.js\"></script>\n"
            + "    <script type=\"text/javascript\" src=\"/webclient/FocusClientMessages/FocusClientMessages.nocache.js\"></script>\n"
            + "  <script type=\"text/javascript\" src=\"/ro/L2RjOWVmYTJkNzQ5ODlhNGIxZWNjNjY2NTk3NDdjOGE3NDIwMmRjY2MuanM\n"
            + "/LzVhNTI5YTY5ZGU4MWRhMDM5ZmQ2ZDM0NTVmNDc5YjU1MGIzOGMwOWUuanM\n"
            + "/LzZhNWE1ZjQ4ZjNiNDVkY2YzNzc5ODc1Mzk2MDU5YWM5MWI4NmMyOTYuanM\n"
            + "/L2UyZTEwNjVlZjUyZjc3NWZlM2Q3YTExY2IxZDgxMTllYzMwMThkNGIuanM\n"
            + "/L2Y4Njc2ZmE4NzA5MTVlYjcxODllOWJkNjZiODg0OGE2ZjQ0NzZlMWQuanM\n"
            + "/LzZjNTdhOTU4YWUzNjkwOGJjYjM1MGQ0MmM2YmZhMGUyMDliYzIwYTQuanM\n"
            + "/-908676147.js\"></script><script type=\"text/javascript\">window.__EVERNOTE_ACTIONBEAN__ = {\"userIdentityIds\":[12225894],\"userInET\":false,\"thirdPartyLoginEnabled\":true,\"gnomeRedesignEnabled\":false,\"gnomeFreeTrialEnabled\":true,\"wechatLoginPath\":\"https://static.app.yinxiang.com/embedded-web/web/wechat-login\",\"evernoteServiceBaseUrl\":\"https://www.evernote.com\",\"userShardId\":\"s59\",\"businessShardId\":null,\"slotSuffix\":\"\",\"slotPrefix\":\"\",\"currentUserId\":28246040,\"currentUserName\":\"Liangcheng Juves\",\"userPrivilege\":\"NORMAL\",\"userServiceLevel\":\"BASIC\",\"websocketUrl\":\"wss://ws.app.yinxiang.com/shard/s59/id\",\"headerJson\":{},\"endpoints\":{\"noteStoreUrl\":\"https://app.yinxiang.com/shard/s59/notestore\",\"userStoreUrl\":\"https://app.yinxiang.com/shard/s59/edam/user\",\"utilityUrl\":\"https://app.yinxiang.com/shard/s59/utility\",\"messageStoreUrl\":\"https://app.yinxiang.com/shard/s59/messagestore\",\"noteStoreUrlForBusiness\":null,\"noteStoreUrlForBusinessAdmin\":null,\"userStoreUrlForBusiness\":null,\"utilityUrlForBusiness\":null,\"businessServiceUrl\":null},\"thriftEndpointBuilderConfig\":{\"shardUrlPrefix\":\"https://app.yinxiang.com/shard/\",\"messageStoreUrlSuffix\":\"/messagestore\",\"noteStoreUrlSuffix\":\"/notestore\",\"userStoreUrlSuffix\":\"/edam/user\",\"businessServiceUrlSuffix\":\"/edam/business\",\"communicationEngineUrlSuffix\":\"/communicationengine\",\"utilityStoreUrlSuffix\":\"/utility\"},\"cdnUIHostPath\":\"https://static.app.yinxiang.com\",\"newTierSubscriptionRedirectUrl\":\"https://static.app.yinxiang.com/pay/subscriptions\",\"newTierSubscriptionManageRedirectUrl\":\"https://static.app.yinxiang.com/pay/subscriptions/manage\",\"userOnlyInET\":false};</script><script type=\"text/javascript\">require(['es6'], function() {});</script><script type=\"text/javascript\">define(\"actionBean\", [], function() {return window.__EVERNOTE_ACTIONBEAN__;});</script><script type=\"text/javascript\" src=\"/redesign/global/js/i18n/i18nMessages_zh_CN_evernoteChina.js?version=1427\"></script><script type=\"text/javascript\" src=\"/ro/L2EyNDRjNzlhOTEzZDQwY2ZkMTNjMjVjMTIwYzQzYmI4N2VhYjY5NTkuanM\n"
            + "/LzcwNDg4MTAzMDViZDdmYjQ1OTQzNWI4MmMyZjE2MmNmZjRkOGUzNWEuanM\n"
            + "/726862830.js\"></script><!--[if !IE]><!--><script>if (/*@cc_on!@*/false) document.documentElement.className += ' ie10';</script><!--<![endif]--></body></html>";

    String encodedString = Base64.encodeToString(string);
    String encodedUrl = Base64.urlEncodeToString(url);
    String encodedText = Base64.mimeEncodeToString(text);

    System.out.println(String.format("encodedString >>>\n %s", encodedString));
    System.out.println(String.format("encodedUrl >>>\n %s", encodedUrl));
    System.out.println(String.format("encodedText >>>\n %s", encodedText));

    System.out.println();
    System.out.println("//////////////////////////////////////////////////////");
    System.out.println("//////////////////////////////////////////////////////");
    System.out.println();

    System.out.println(
        String.format("decodedString >>>\n %s", Base64.decodeToString(encodedString)));
    System.out.println(String.format("decodedUrl >>>\n %s", Base64.urlDecodeToString(encodedUrl)));
    System.out.println(
        String.format("decodedText >>>\n %s", Base64.mimeDecodeToString(encodedText)));
  }
}
