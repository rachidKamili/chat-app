package me.kamili.rachid.chatapp.view.base;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}