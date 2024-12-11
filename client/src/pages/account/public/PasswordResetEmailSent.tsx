import {Card, CardDescription, CardHeader, CardTitle} from '@/components/ui/card';
import PublicLayoutContainer from '@/shared/layout/PublicLayoutContainer';
import {MailCheck} from 'lucide-react';

const PasswordResetEmailSent = () => {
    return (
        <PublicLayoutContainer>
            <Card className="mx-auto flex max-w-sm flex-col gap-6 rounded-xl p-6 text-start shadow-none">
                <MailCheck className="mx-auto size-12" />

                <CardHeader className="p-0">
                    <CardTitle className="self-center text-xl font-bold text-content-neutral-primary">
                        Please check your email
                    </CardTitle>

                    <CardDescription className="self-center text-content-neutral-secondary">
                        We sent an email to m@example.com. <br /> If you can&apos;t find it check the spam folder
                    </CardDescription>
                </CardHeader>

                <div className="flex justify-center gap-1 text-sm">
                    <span className="text-content-neutral-secondary">Didn&apos;t get an email?</span>

                    <button className="font-bold hover:text-content-neutral-secondary text-content-neutral-primary underline">
                        Click to resend
                    </button>
                </div>
            </Card>
        </PublicLayoutContainer>
    );
};

export default PasswordResetEmailSent;
