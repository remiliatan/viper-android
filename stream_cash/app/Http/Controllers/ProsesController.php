<?php

namespace App\Http\Controllers;
 
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Response;

class ProsesController extends Controller
{
    function daftarUser(Request $request){


    	//cek apakah ada email sama
    	$cekEmail = DB::table('user')->where('emailUser', $request->emailUser)->get();

    	if(count($cekEmail) > 0){
    		jsonEncode(0, "E-Mail sudah digunakan, coba yang lain");
    	}else{
    		//query insert
			$insertDB = DB::table('user')->insert([
    		'emailUser' => $request->emailUser,
    		'passwordUser' => $request->passwordUser,
    		'namaUser' => $request->namaUser,
    		'balanceUser' => 0
    		]);

    		//jika daftar berhasil
	    	if($insertDB){
    			jsonEncode(1, "Berhasil daftar, silahkan login");
	    	}else{
    			jsonEncode(0, "Gagal mendaftar");
	    	}
    	}
    }

    function loginUser(Request $request){

    	$cekRow = DB::table('user')->where('emailUser', $request->emailUser)->where('passwordUser', $request->passwordUser)->get();
    	if(count($cekRow) > 0){
    		jsonEncode(1, "Selamat datang");
    	}else{
    		
    		jsonEncode(0, "E-Mail atau password salah");
    	}
    }

    function topup(Request $request){

    	$emailTarget = $request->targetEmail;
    	$nominal = $request->nominalTopUp;
    	$apiKey = "88989291smaka812jae";
    	$keyResponse = $request->apiKey;

    	if($apiKey == $keyResponse){
    		$cekData = DB::table('user')->where('emailUser', $emailTarget)->get();
	    	if(count($cekData) > 0){

	    			//ambil balance sebelumnya
		    		foreach ($cekData as $value) {
		    			$balanceSebelumnya = $value->balanceUser;
		    		}
		    			
		    		$finalBalance = (int)$nominal + (int)$balanceSebelumnya;
		    		

		    		$updateData = DB::table('user')->where('emailUser', $emailTarget)->update([
		    			"balanceUser" => $finalBalance
		    		]);
		    		if($updateData){
		    			jsonEncode(1, "Berhasil diproses");
		    		}else{
		    			jsonEncode(0, "Gagal, mohon coba lagi");
		    		}
	    		

	    	}else{
	    		jsonEncode(0, "E-Mail penerima tidak ditemukan");
	    	}
    	}else{
    		jsonEncode(0, "Dilarang");
    	}

    	
    }

    function transfer(Request $request){
    	$targetEmail = $request->targetEmail;
    	$pengirimEmail = $request->pengirimEmail;
    	$nominal = $request->nominalTopUp;
    	$apiKey = "88989291smaka812jae";
    	$keyResponse = $request->apiKey;

    		if($keyResponse == $apiKey){
    		$cekData = DB::table('user')->where('emailUser', $targetEmail)->get();
	    	if(count($cekData) > 0){
	    			$datapengirim = DB::table('user')->where('emailUser', $pengirimEmail)->get();
	    			//ambil balance sebelumnya
		    		foreach ($cekData as $value) {
		    			$balanceSebelumnya = $value->balanceUser;
		    		}
		    		foreach ($datapengirim as $key) {
		    			$balancePengirim = $key->balanceUser;
		    		}	
		    		if($balancePengirim >= $nominal){
			    		$finalBalancePenerima = (int)$nominal + (int)$balanceSebelumnya;
			    		$finalBalancePengirim = (int)$balancePengirim - (int)$nominal;

			    		$updateData = DB::table('user')->where('emailUser', $targetEmail)->update([
			    			"balanceUser" => $finalBalancePenerima
			    		]);

			    		$updateData2 = DB::table('user')->where('emailUser', $pengirimEmail)->update([
			    			"balanceUser" => $finalBalancePengirim
			    		]);

			    		if($updateData){
			    			jsonEncode(1, "Berhasil diproses");
			    		}else{
			    			jsonEncode(0, "Gagal, mohon coba lagi");
			    		}
		    		}else{
		    			jsonEncode(0, "Saldo kurang");
		    		}
	    	}else{
	    		jsonEncode(0, "E-Mail penerima tidak ditemukan");
	    	}
    	}else{
    		jsonEncode(0, "Dilarang");
    	}

    }

    function withdraw(Request $request){
    	$emailTarget = $request->targetEmail;
    	$nominal = $request->nominalWithdraw;
    	$apiKey = "88989291smaka812jae";
    	$keyResponse = $request->apiKey;

    	if($apiKey == $keyResponse){
    		$cekData = DB::table('user')->where('emailUser', $emailTarget)->get();
	    	if(count($cekData) > 0){

	    			//ambil balance sebelumnya
		    		foreach ($cekData as $value) {
		    			$balanceSebelumnya = $value->balanceUser;
		    		}

		    		//jika balance kurang
		    		if($balanceSebelumnya >= $nominal){
		    			$finalBalance = (int)$nominal - (int)$balanceSebelumnya;

			    		$finalBalance = (int)$balanceSebelumnya - (int)$nominal;

		    			$updateData = DB::table('user')->where('emailUser', $emailTarget)->update([
		    				"balanceUser" => $finalBalance
		    			]);
		    			if($updateData){
		    				jsonEncode(1, "Berhasil ditransfer");
		    			}else{
		    				jsonEncode(0, "Gagal, mohon coba lagi");
		    			}
		    		}else{
		    				jsonEncode(0, "Saldo tidak cukup");
		    		}

		    			
	

	    	}else{
	    		jsonEncode(0, "E-Mail penerima tidak ditemukan");
	    	}
		    		
    	}else{
    		jsonEncode(0, "Dilarang");
    	}
    }

    
}
function jsonEncode($angka, $pesan){
    $output["success"] = $angka;
    $output["message"] = $pesan;
   	echo json_encode($output);
}
