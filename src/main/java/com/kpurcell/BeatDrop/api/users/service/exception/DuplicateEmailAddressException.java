package com.kpurcell.BeatDrop.api.users.service.exception;

public class DuplicateEmailAddressException extends IllegalArgumentException
{
    public DuplicateEmailAddressException()
    {
        super("An account with this email address already exists");
    }
}
