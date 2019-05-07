package com.example.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by baixiao on 2019/4/2.
 */


@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope