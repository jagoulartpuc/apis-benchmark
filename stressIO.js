import http from 'k6/http';

export const options = {
    scenarios: {
        contacts: {
            executor: 'ramping-arrival-rate',
            preAllocatedVUs: 50,
            timeUnit: '1s',
            startRate: 50,
            stages: [
                { target: 200, duration: '30s' }, // linearly go from 50 iters/s to 200 iters/s for 30s
                { target: 500, duration: '0' }, // instantly jump to 500 iters/s
                { target: 500, duration: '1m' }, // continue with 500 iters/s for 10 minutes
            ],
        },
    },
};


export default function () {
    const url = 'http://localhost:8080/user';

    http.get(url);
    const payload = JSON.stringify({
        username: 'fulano',
        email: 'aaa',
        password: 'bbb',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    http.post(url, payload, params);
}
