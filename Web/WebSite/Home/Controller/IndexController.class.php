<?php
namespace Home\Controller;
use Think\Controller;
class IndexController extends Controller {
    public function indexAction(){
		$this->display();
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
		
		$data['name'] = "test";
		$resutdata = $orderinfo->where($condition2)->select();

		$this->assign('list', $resutdata);
		$this->display("status");
	}
}