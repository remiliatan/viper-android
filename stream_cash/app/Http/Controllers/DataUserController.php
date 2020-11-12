<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Response;

class DataUserController extends Controller
{
    function tampilData(Request $request){
    	$emailUser = $request->emailUser;
    	$ambilData = DB::table('user')->where('emailUser', $emailUser)->get();
    	foreach ($ambilData as $data) {
    		$resonse["userNama"] = "Halo, ".$data->namaUser;
    		$resonse["userBalance"] = "Rp. ".number_format($data->balanceUser,  0, ',', '.');
    	}
    	echo json_encode($resonse);

    }
}
