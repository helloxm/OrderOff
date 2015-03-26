<?php
namespace Home\Controller;
use Think\Controller;
class IndexController extends Controller {
    public function indexAction(){
		//$User = M('User','other_','mysql://root:1234@localhost/demo#utf8'); 
		//$patient = M('patientinfo', '', 'mysql://root:123456@localhost:3306/offorder#utf8'); 
		//$patient = M('patientinfo');
		//$resutdata = $patient->where('id > 0')->select(); 
		//dump($resutdata);
		
		$this->display("",'GB2312');
       //$this->show('<style type="text/css">*{ padding: 0; margin: 0; } div{ padding: 4px 48px;} body{ background: #fff; font-family: "寰蒋闆呴粦"; color: #333;font-size:24px} h1{ font-size: 100px; font-weight: normal; margin-bottom: 12px; } p{ line-height: 1.8em; font-size: 36px } a,a:hover,{color:blue;}</style><div style="padding: 24px 48px;"> <h1>:)</h1><p>娆㈣繋浣跨敤 <b>ThinkPHP</b>锛�</p><br/>鐗堟湰 V{$Think.version}</div><script type="text/javascript" src="http://ad.topthink.com/Public/static/client.js"></script><thinkad id="ad_55e75dfae343f5a1"></thinkad><script type="text/javascript" src="http://tajs.qq.com/stats?sId=9347272" charset="UTF-8"></script>','utf-8');
    }
	
	public function loginAction()
    {
    	$patient = M('patientinfo', '', 'mysql://root:123456@localhost:3306/offorder#utf8'); 
		$condition['card_id'] = $_POST['user'];
		$condition['password'] = $_POST['password'];
		$resutdata = $patient->where($condition)->sum('id'); 
		//dump($condition);
		
		//dump($resutdata);
		if ($resutdata <= 0) {
			echo "<script>{window.alert('用户名或者密码错误！');location.href='index.html'} </script>";
			return;
		}
    	
		$orderinfo = M('orderinfo', '', 'mysql://root:123456@localhost:3306/offorder#utf8'); 
		
		$condition2['card_id'] = $_POST['user'];
		$resutdata = $orderinfo->where($condition2)->select();

		//$this->assign()
		$this->display("status",'GB2312');
		
        //$this->show('<style type="text/css">*{ padding: 0; margin: 0; } div{ padding: 4px 48px;} body{ background: #fff; font-family: "寰蒋闆呴粦"; color: #333;font-size:24px} h1{ font-size: 100px; font-weight: normal; margin-bottom: 12px; } p{ line-height: 1.8em; font-size: 36px } a,a:hover,{color:blue;}</style><div style="padding: 24px 48px;"> <h1>:)</h1><p>娆㈣繋浣跨敤777  <b>ThinkPHP</b>锛�</p><br/>鐗堟湰 V{$Think.version}</div><script type="text/javascript" src="http://ad.topthink.com/Public/static/client.js"></script><thinkad id="ad_55e75dfae343f5a1"></thinkad><script type="text/javascript" src="http://tajs.qq.com/stats?sId=9347272" charset="UTF-8"></script>','utf-8');
    }
}