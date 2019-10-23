package com.springjpa.functionalInterface;

import com.springjpa.model.UserDetail;

@FunctionalInterface
public interface BonusComponent {

	UserDetail updatedUserWithBonus(UserDetail userDetail, String bonusAmount);
}
