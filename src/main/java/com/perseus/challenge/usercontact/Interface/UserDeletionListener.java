package com.perseus.challenge.usercontact.Interface;

import java.util.List;

public interface UserDeletionListener {

    void onDeleteUser(List<Integer> userEmailIds, List<Integer> userPhoneNumberIds);
}
