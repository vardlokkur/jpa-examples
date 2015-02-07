@TypeDefs({
    @TypeDef(defaultForType = boolean.class, typeClass = YesNoType.class),
    @TypeDef(defaultForType = Boolean.class, typeClass = YesNoType.class),
    @TypeDef(defaultForType = LocalDate.class, typeClass = PersistentLocalDate.class),
    @TypeDef(defaultForType = LocalDateTime.class, typeClass = PersistentLocalDateTime.class)
}) package com.blogspot.vardlokkur.domain.model;


import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.type.YesNoType;
import org.jadira.usertype.dateandtime.threeten.PersistentLocalDate;
import org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;