package com.kpurcell.BeatDrop.api.users.service.exception;

public class InvalidNewUserParametersException extends IllegalArgumentException
{
    public InvalidNewUserParametersException()
    {
        super("The parameters used to try and register a new user were invalid");
    }
}
