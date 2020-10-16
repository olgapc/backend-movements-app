package com.movements.springboot.backend.apirest.auth;

public class JwtConfig {
	
	public static final String SECRET_KEY = "quelque.clée.secrète.%/85%/%$";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEA3YHF50jwlaNyj+pKkh1Kb4mtnZbw+vrkdJWGQGLJa1KbXo2i\n" + 
			"HafzCo5JoshndptWrDuYujhQSurx4BkRSl7kFRJ6e/gji49t1YXxsQbCKB/h0vth\n" + 
			"SoRt6uPIOnqk6c+wnMXaTwBHV8HVHiOM40LbKmElXKrsWV2ejKNYQX0eUmRnDa9i\n" + 
			"9Sjd7MkJZM3k+9lpvTowSULOuCkcDPWRTYh+jsDdyXb+KfWDViCtCzHxru844AcF\n" + 
			"vH5tgNiqJd/B2k4ESCpRNTJyWVuNJFL1amJdFFD+ya8MTBPQsnwmODKNXFI2Qxd+\n" + 
			"my1PTT2bbmdE+7jCX13RWIAGV9btPVX2sKNwFQIDAQABAoIBAFYu9uxNUeY26Q0b\n" + 
			"sJX3NY3+OIHXR72vPpIp10lX5lyOkIsSemAlh4e04uumI7NfLzLmIT9bR8pObXuu\n" + 
			"5S5Vi1KzlNqFKndTf/2TFv20znhEJ14qlqkiq423wKEYNPAP8fydcppIVnnLnfsT\n" + 
			"A+8shQ+RKrNKgZkntterelxIrTKKysG7GtdDwHHlrkrmbPchi632nU1wTiArXSv1\n" + 
			"mhPwJ9dG/SA9MarnqsqNVHRitiYcpjkKdeq8vjuFwuPcw/BzRQCHQ7dkNtou2ZO1\n" + 
			"eJpHMUuoXugAY3yrweCy4M7LHwzAH5m+/GPPoox+yVL1rEpupUgcaMT1uKg3Mnnb\n" + 
			"Qwy9DUUCgYEA8xlAg7uAXXE1e9NhwrTi7cAQqm19tIAZe1tGUI+9iK57ydkGVH88\n" + 
			"seQ+zIrhlZXsOKZ4DuuHkiaXpUDLklBmi3Lub8xXQYNUPLfesRmN1rgKPbblrB7o\n" + 
			"ggPWNgXtpDKhZ73wKkV4dTREZnhvHSnthan2kSKB2EddfI0drEVQjIMCgYEA6UMs\n" + 
			"1fHmMCtbz6m7KsPe2l29g5vsBTOJzmvXXuzQ5WWOsb1QsALLy8pUf8W/BWCYnik6\n" + 
			"KPO8IBx10sVWSdmv1oZMwS+XSlpNrJTnPMIoyqQu9GRitz1FLxfQyECGsR9/q1pJ\n" + 
			"5Po8PhSpwVZRy/S+DtmhHDw7i76c5KUy81h/nYcCgYEAmRd2AX+7Px1kSS69ihjQ\n" + 
			"MFmcJTf+ygVCBuVcSq+69LCtVFhQHDZkysKqqbVsiv93W9W6FetN3URgi7laejIX\n" + 
			"4gnnDpDpAmDqU7KXk4A8StRr3qGn0dVFJ1uzMyJYneckrmh3LqfBTE42JTIDB6K3\n" + 
			"6k028f+BNer7fMyBu1OLfnECgYEAgI9dbqzbyUCcYV+SoIdDk+aGRLry4gr9sJ8P\n" + 
			"HGo417WZZ6yHg/qGh9MDr0g1d6k9dC4Ut+1difUlmvzYCevIVdu/AdpQ79HFbZDD\n" + 
			"wWDhSo4ist+A6K0ELGhb0YJ+ubA6lR42C9U+zphk1wHUIyzIczbO7NjlmKd9oSbL\n" + 
			"ZgMokpECgYB6Jcz2M5lwkwOIANzBnPRkq77vTCWQJFrRFIWkqc6PiO4aOkv3BxVl\n" + 
			"p4vqUvwXPj9Rf//MbXDDlFQGNrPmON9jhK7znoXjrqJFC0ObGgqlm28kJGKTdr5b\n" + 
			"43l54tlYukzTURQHmx9VscrkH2IfwSG9OGB2kXJtemjPjJwMv4fWsA==\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3YHF50jwlaNyj+pKkh1K\n" + 
			"b4mtnZbw+vrkdJWGQGLJa1KbXo2iHafzCo5JoshndptWrDuYujhQSurx4BkRSl7k\n" + 
			"FRJ6e/gji49t1YXxsQbCKB/h0vthSoRt6uPIOnqk6c+wnMXaTwBHV8HVHiOM40Lb\n" + 
			"KmElXKrsWV2ejKNYQX0eUmRnDa9i9Sjd7MkJZM3k+9lpvTowSULOuCkcDPWRTYh+\n" + 
			"jsDdyXb+KfWDViCtCzHxru844AcFvH5tgNiqJd/B2k4ESCpRNTJyWVuNJFL1amJd\n" + 
			"FFD+ya8MTBPQsnwmODKNXFI2Qxd+my1PTT2bbmdE+7jCX13RWIAGV9btPVX2sKNw\n" + 
			"FQIDAQAB\n" + 
			"-----END PUBLIC KEY-----";

}
