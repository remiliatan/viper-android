<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/daftar', 'App\Http\Controllers\ProsesController@daftaruser');
Route::post('/login', 'App\Http\Controllers\ProsesController@loginUser');
Route::post('/data', 'App\Http\Controllers\DataUserController@tampilData');
Route::post('/topup', 'App\Http\Controllers\ProsesController@topup');
Route::post('/transfer', 'App\Http\Controllers\ProsesController@transfer');
