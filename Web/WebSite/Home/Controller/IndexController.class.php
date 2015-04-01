<?php
namespace Home\Controller;
use Think\Controller;
class IndexController extends Controller {
    public function indexAction(){
		$this->display();
      }
	
	public function loginAction()
    {
    	if (strlen($_POST['user']) <= 0) {
			echo "<script>{window.alert('用户名不能为空！');location.href='index.html'} </script>";
			return;
		}
		
    	if (strlen($_POST['password']) <= 0) {
			echo "<script>{window.alert('密码不能为空！');location.href='index.html'} </script>";
			return;
		}
		
		$patient = M('patientinfo', '', 'mysql://root:123456@localhost:3306/offorder#utf8'); 
		$condition['card_id'] = $_POST['user'];
		$resutdata = $patient->where($condition)->sum('id'); 
		
    	if ($resutdata <= 0) {
			echo "<script>{window.alert('用户不存在！');location.href='index.html'} </script>";
			return;
		}
		
		$condition['card_id'] = $_POST['user'];
		$condition['password'] = $_POST['password'];
		$resutdata = $patient->where($condition)->sum('id'); 
		
		if ($resutdata <= 0) {
			echo "<script>{window.alert('密码错误！');location.href='index.html'} </script>";
			return;
		}
    	
		$orderinfo = M('orderinfo', '', 'mysql://root:123456@localhost:3306/offorder#utf8'); 
		$data['card_id'] = $_POST['user'];
		$resutdata = $orderinfo->where($condition2)->select();

		$this->assign('list', $resutdata);
		$this->display("status");
	}
}