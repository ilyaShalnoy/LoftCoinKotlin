package com.example.notes.loftcoinkotlin.data.fcm

import com.example.notes.loftcoinkotlin.R
import com.example.notes.loftcoinkotlin.baseComponent
import com.example.notes.loftcoinkotlin.core.util.Notifier
import com.example.notes.loftcoinkotlin.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class FcmService : FirebaseMessagingService() {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var notifier: Notifier

    override fun onCreate() {
        super.onCreate()
        val baseComponent = this.baseComponent
        DaggerFcmComponent.builder().baseComponent(baseComponent)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val notification = message.notification
        if (notification != null) {
            disposable.add(
                notifier.sendMessage(
                    Objects.toString(notification.title, getString(R.string.app_name)),
                    Objects.toString(notification.body, "Something wrong"),
                    MainActivity::class.java
                ).subscribe()
            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }


    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}