## MS_MESSAGE_SERVICE

### V1.0.0

The goal of V1.0.0 is to finish the function of sending email/SMS verify code to destination users.

The future goal of this service could be extended to send batch inform emails, SMS or in- platform information.

That's what message means.

#### V1.0.0 Function List
1. Email and SMS code: generate and send.
2. Repository: Store the sending record.
3. RPC: Instead the complex but high effienct grpc, I choose Spring cloud's Openfeign instead, it's simpler and more suits for spring frameworks.
