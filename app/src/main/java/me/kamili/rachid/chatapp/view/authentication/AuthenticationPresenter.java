package me.kamili.rachid.chatapp.view.authentication;

public class AuthenticationPresenter implements AuthenticationContract.Presenter {

    AuthenticationContract.View view;


    @Override
    public void attachView(AuthenticationContract.View view) {
        this.view = view;
    }

    /*@Override
    public void translate(Sentence sentence) {
        //view.onTranslation(Translator.toPigLatin(sentence));
    }*/

    @Override
    public void detachView() {
        this.view = null;
    }


}
