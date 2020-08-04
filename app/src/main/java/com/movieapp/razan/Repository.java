package com.movieapp.razan;


import com.movieapp.razan.login.model.UsersModel;

class Repository {

    private UsersModel usersModel = new UsersModel();

    private static Repository instance;

    public  static Repository getInstance(){
        if(instance==null){
            instance=new Repository();
        }
        return instance;
    }

   // public MutableLiveData<UsersModel>getData{}
}
