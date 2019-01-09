package org.lyncc.bazinga.rx.bazinga.msentinel.slots.authority;

import org.lyncc.bazinga.rx.bazinga.msentinel.slots.block.BlockException;

public class AuthorityException extends BlockException {

    public AuthorityException(String ruleLimitApp) {
        super(ruleLimitApp);
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityException(String ruleLimitApp, String message) {
        super(ruleLimitApp, message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
