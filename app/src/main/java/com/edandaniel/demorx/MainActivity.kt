package com.edandaniel.demorx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clubesObservable = Observable.just("Palmeiras","São Paulo", "Corinthians", "Santos")

        clubesObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter{it.toUpperCase().startsWith("S")}
                .subscribe(getClubesObserver())
    }

    private fun getClubesObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG", "onSubscribe")
            }

            override fun onNext(clube: String) {
                //tvClubes.text = "${tvClubes.text}\n$clube"
                Log.d("TAG", "Nome: $clube")
            }

            override fun onError(e: Throwable) {
                Log.e("TAG", "Erro: " + e.message)
            }

            override fun onComplete() {
                Log.d("TAG", "Todos os itens emitidos")


            }
        }
    }
}
